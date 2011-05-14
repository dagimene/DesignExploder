package designexploder.model.classnode.build;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import designexploder.model.build.ModelBuilder;
import designexploder.model.build.ModelDataProvider;
import designexploder.model.build.ModelDataProvider.ConnectionDataProvider;
import designexploder.model.build.ModelFactory;
import designexploder.model.build.ModelFactory.ConnectionBuilder;
import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.ClassConnection;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.ClassType;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.ModelUtil;
import designexploder.model.classnode.Type;
import designexploder.model.classnode.build.ClassNodeModelDataProvider.ClassConnectionDataProvider;
import designexploder.model.classnode.build.ClassNodeModelFactory.ClassConnectionBuilder;

public class ClassNodeModelBuilder<N extends ClassNode<C>,C extends ClassConnection> extends ModelBuilder<N, C>{
	
	private Map<Type, Set<HalfDefinedRelation>> halfDefinedRelationsMap = new HashMap<Type, Set<HalfDefinedRelation>>();

	public ClassNodeModelBuilder(ModelDataProvider modelDataProvider, ModelFactory<N, C> modelFactory) {
		super(modelDataProvider, modelFactory);
	}

	@Override
	protected <T extends ConnectionDataProvider> void buildAllConnections(Iterator<T> providers) {
		super.buildAllConnections(providers);

		// Create connections from classes.
		for (N node : nodes.values()) {
			if(!node.getType().isClassType()) {
				continue;
			}
			// Resolve half defined relations
			ClassType classType = node.getType().asClassType();
			Set<HalfDefinedRelation> halfDefinedRelations = halfDefinedRelationsMap.get(classType.getName());
			if(halfDefinedRelations != null) {
				for (HalfDefinedRelation halfDef : halfDefinedRelations) {
					buildConnection(new ClassConnectionDataProviderImpl(halfDef.sourceMissing ? halfDef.endpoint : node,
							halfDef.sourceMissing ? node : halfDef.endpoint, halfDef.nature));
				}
			}

			// Find new relations
			
			// Hierarchy
			if(!classType.isEnum()) {
				List<ClassType> supertypes = classType.isInterface() ?
					classType.getInterfaces() :
					Collections.singletonList(classType.getSuperclass());
				buildRelations(node, supertypes, DexConstant.HIERARCHY);
			}
			
			// Realization
			if(!classType.isInterface()) {
				List<ClassType> interfaces = classType.getInterfaces();	
				buildRelations(node, interfaces, DexConstant.REALIZATION);
			}
			
			// Composition and Association
			for (Attribute attribute : node.getAttributes()) {
				Type attributeType = attribute.getType();
				if(!attribute.getType().isBasic() && !attribute.isStatic()) {
					if(attributeType.isArray()) {
						buildRelation(node, attributeType.asArrayType().getInnerType().asClassType(), DexConstant.COMPOSITION);
					} else {
						ClassType attributeClassType = attributeType.asClassType();
						buildRelation(node, attributeClassType, DexConstant.ASSOCIATION);
						if(ModelUtil.isCollection(attributeClassType)) {
							buildRelations(node, attributeClassType.getTypeParameters(), DexConstant.COMPOSITION);
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void loadConnectionData(ConnectionDataProvider connectionData,
			ConnectionBuilder<C> connectionBuilder) {
		ClassNodeModelFactory.ClassConnectionBuilder<C> builder = (ClassConnectionBuilder<C>) connectionBuilder; 
		ClassConnectionDataProvider provider = (ClassConnectionDataProvider) connectionData;
		builder.setNature(provider.getNature());
		builder.setSourceCardinality(provider.getSourceCardinality());
		builder.setTargetCardinality(provider.getTargetCardinality());
		builder.setName(provider.getName());
		super.loadConnectionData(connectionData, connectionBuilder);
	}
	
	private void buildRelations(N node, Collection<? extends Type> targets, DexConstant nature) {
		for (Type target : targets) {
			if(target.isClassType()) {
				buildRelation(node, target.asClassType(), nature);
			}
		}
	}
	
	private void buildRelation(N node, ClassType target, DexConstant nature) {
		N supertypeNode = nodes.get(target.getName());
		if(supertypeNode != null) {
			buildConnection(new ClassConnectionDataProviderImpl(node, supertypeNode, nature));
		} else {
			Set<HalfDefinedRelation> halfDefinedRelations = halfDefinedRelationsMap.get(target.getName());
			if(halfDefinedRelations == null) {
				halfDefinedRelations = new HashSet<HalfDefinedRelation>();
				halfDefinedRelationsMap.put(target, halfDefinedRelations);
			}
			halfDefinedRelations.add(new HalfDefinedRelation(node, nature));
		}
	}
	
	public class ClassConnectionDataProviderImpl implements ClassConnectionDataProvider {
		private final N target;
		private final N source;
		private final DexConstant nature;

		public ClassConnectionDataProviderImpl(N source, N target, DexConstant nature) {
			this.source = source;
			this.target = target;
			this.nature = nature;
		}

		@Override
		public String getSourceID() {
			return source.getId();
		}

		@Override
		public String getTargetID() {
			return target.getId();
		}

		public DexConstant getNature() {
			return nature;
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public int getSourceCardinality() {
			return 0;
		}

		@Override
		public int getTargetCardinality() {
			return 0;
		}
	}

	class HalfDefinedRelation {
		N endpoint;
		boolean sourceMissing;
		private final DexConstant nature;

		public HalfDefinedRelation(N endpoint, DexConstant nature) {
			this(endpoint, nature, false);
		}
		
		public HalfDefinedRelation(N endpoint, DexConstant nature,
				boolean sourceMissing) {
			this.endpoint = endpoint;
			this.nature = nature;
			this.sourceMissing = sourceMissing;
		}
	}
}