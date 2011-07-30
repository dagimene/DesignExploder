
package designexploder.model.extension.classnode.build;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.BaseModelBuilder;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassConnection;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.ModelUtil;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.classnode.impl.ClassModelFactory;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.IdUtil;

public class ClassRelationsModelBuilder extends BaseModelBuilder {
	
	private Map<Type, Set<HalfDefinedRelation>> halfDefinedRelationsMap = new HashMap<Type, Set<HalfDefinedRelation>>();

	@Override
	public NodeContainer build(NodeContainer diagram) {
		return buildClassConnections(super.build(diagram));
	}

	private NodeContainer buildClassConnections(NodeContainer diagram) {
		// Create connections from classes.
		for (Node node : diagram.getNodes()) {
			ClassNode classNode = node.getExtension(ClassNode.class);
			if(classNode == null || !classNode.getType().isClassType()) {
				continue;
			}
			
			// Resolve half defined relations
			ClassType classType = classNode.getType().asClassType();
			Set<HalfDefinedRelation> halfDefinedRelations = halfDefinedRelationsMap.get(classType);
			if(halfDefinedRelations != null) {
				for (HalfDefinedRelation halfDef : halfDefinedRelations) {
					buildConnection(halfDef.sourceMissing ? halfDef.endpoint : node, halfDef.sourceMissing ? node : halfDef.endpoint, halfDef.nature);
				}
			}

			// Find new relations
			
			// Hierarchy
			if(!classType.isEnum()) {
				List<ClassType> supertypes = classType.isInterface() ?
					classType.getInterfaces() :
					Collections.singletonList(classType.getSuperclass());
				buildRelations(node, supertypes, ClassModelNatures.HIERARCHY);
			}
			
			// Realization
			if(!classType.isInterface()) {
				List<ClassType> interfaces = classType.getInterfaces();	
				buildRelations(node, interfaces, ClassModelNatures.REALIZATION);
			}
			
			// Composition and Association
			for (Attribute attribute : classNode.getAttributes()) {
				Type attributeType = attribute.getType();
				if(!attribute.getType().isBasic() && !attribute.isStatic()) {
					if(attributeType.isArray()) {
						buildRelation(node, attributeType.asArrayType().getInnerType().asClassType(), ClassModelNatures.COMPOSITION);
					} else {
						ClassType attributeClassType = attributeType.asClassType();
						buildRelation(node, attributeClassType, ClassModelNatures.ASSOCIATION);
						if(ModelUtil.isCollection(attributeClassType)) {
							buildRelations(node, attributeClassType.getTypeParameters(), ClassModelNatures.COMPOSITION);
						}
					}
				}
			}
		}
		return diagram;
	}
	
	private void buildRelations(Node node, Collection<? extends Type> targets, ClassModelNatures nature) {
		for (Type target : targets) {
			if(target.isClassType()) {
				buildRelation(node, target.asClassType(), nature);
			}
		}
	}
	
	private void buildRelation(Node node, ClassType target, ClassModelNatures nature) {
		target = target.getTypeErasure();
		Node targetNode = findNode(IdUtil.createClassId(target.getName()).toString());
		if(targetNode != null) {
			buildConnection(node, targetNode, nature);
		} else {
			Set<HalfDefinedRelation> halfDefinedRelations = halfDefinedRelationsMap.get(target.getName());
			if(halfDefinedRelations == null) {
				halfDefinedRelations = new HashSet<HalfDefinedRelation>();
				halfDefinedRelationsMap.put(target, halfDefinedRelations);
			}
			halfDefinedRelations.add(new HalfDefinedRelation(node, nature));
		}
	}
	
	protected void buildConnection(Node source, Node target, ClassModelNatures nature) {
		Connection connection = BasicModelFactory.getInstance().createConnection();
		ClassConnection classConnection = ClassModelFactory.getInstance().createClassConnection();
		classConnection.setNature(nature);
		connection.setId(IdUtil.creteConnectionId(source.getId(), target.getId()).toString());
		connection.addExtension(ClassConnection.class, classConnection);
		connection.setSource(source);
		connection.setTarget(target);
		connection.getSource().addOutflow(connection);
		connection.getTarget().addInflow(connection);
	}
	
	/**
	 * Half defined relations are not in use actually,
	 * since all nodes are built before connections construction begins.
	 */
	class HalfDefinedRelation {
		Node endpoint;
		boolean sourceMissing;
		private final ClassModelNatures nature;

		public HalfDefinedRelation(Node endpoint, ClassModelNatures nature) {
			this(endpoint, nature, false);
		}
		
		public HalfDefinedRelation(Node endpoint, ClassModelNatures nature,
				boolean sourceMissing) {
			this.endpoint = endpoint;
			this.nature = nature;
			this.sourceMissing = sourceMissing;
		}
	}

	/*@Override
	protected void loadConnectionData(ConnectionDataProvider connectionData,
			ConnectionBuilder<ClassConnection> connectionBuilder) {
		ClassNodeModelFactory.ClassConnectionBuilder builder = (ClassConnectionBuilder) connectionBuilder; 
		ClassConnectionDataProvider provider = (ClassConnectionDataProvider) connectionData;
		builder.setNature(provider.getNature());
		builder.setSourceCardinality(provider.getSourceCardinality());
		builder.setTargetCardinality(provider.getTargetCardinality());
		builder.setName(provider.getName());
		super.loadConnectionData(connectionData, connectionBuilder);
	}*/
	
}