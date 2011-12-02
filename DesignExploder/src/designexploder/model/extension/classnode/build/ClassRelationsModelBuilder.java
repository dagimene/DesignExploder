
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
import designexploder.model.extension.classnode.ClassRelation;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.ClassModelUtil;
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
					buildConnection(halfDef.sourceMissing ? halfDef.endpoint : node, halfDef.sourceMissing ? node : halfDef.endpoint, halfDef.nature, halfDef.attribute);
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
                if(!attribute.isInherited()) {
                    Type attributeType = attribute.getType();
                    if(!attribute.getType().isBasic() && !attribute.isStatic()) {
                        if(attributeType.isArray()) {
                            buildRelation(node, attribute, attributeType.asArrayType().getInnerType().asClassType(), ClassModelNatures.COMPOSITION);
                        } else {
                            ClassType attributeClassType = attributeType.asClassType();
                            buildRelation(node, attribute, attributeClassType, ClassModelNatures.ASSOCIATION);
                            if(ClassModelUtil.isCollection(attributeClassType)) {
                                buildRelations(node, attribute, attributeClassType.getTypeParameters(), ClassModelNatures.COMPOSITION);
                            }
                        }
                    }
                }
			}
		}
		return diagram;
	}
	
	private void buildRelations(Node node, Collection<? extends Type> targets, ClassModelNatures nature) {
		buildRelations(node, null, targets, nature);
	}

	private void buildRelations(Node node, Attribute attribute, Collection<? extends Type> targets, ClassModelNatures nature) {
		for (Type target : targets) {
			if(target != null && target.isClassType()) {
				buildRelation(node, attribute, target.asClassType(), nature);
			}
		}
	}

	//private void buildRelation(Node node, ClassType target, ClassModelNatures nature) {
	
	private void buildRelation(Node node, Attribute attribute, ClassType target, ClassModelNatures nature) {
		target = target.getTypeErasure();
		Node targetNode = findNode(IdUtil.createClassId(target.getName()).toString());
		if(targetNode != null) {
			buildConnection(node, targetNode, nature, attribute);
		} else {
			Set<HalfDefinedRelation> halfDefinedRelations = halfDefinedRelationsMap.get(target.getName());
			if(halfDefinedRelations == null) {
				halfDefinedRelations = new HashSet<HalfDefinedRelation>();
				halfDefinedRelationsMap.put(target, halfDefinedRelations);
			}
			halfDefinedRelations.add(new HalfDefinedRelation(node, nature, attribute));
		}
	}
	
	protected void buildConnection(Node source, Node target, ClassModelNatures nature, Attribute attribute) {
		Connection connection = BasicModelFactory.getInstance().createConnection();
		ClassRelation classConnection = ClassModelFactory.getInstance().createClassConnection();
		classConnection.setNature(nature);
		classConnection.setOrigin(attribute);
		connection.setId(IdUtil.createConnectionId(source.getId(), target.getId()).toString());
		connection.addExtension(classConnection);
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
		Attribute attribute;
		Node endpoint;
		boolean sourceMissing;
		private final ClassModelNatures nature;

		public HalfDefinedRelation(Node endpoint, ClassModelNatures nature, Attribute attribute) {
			this(endpoint, nature, attribute, false);
		}
		
		public HalfDefinedRelation(Node endpoint, ClassModelNatures nature, Attribute attribute,
				boolean sourceMissing) {
			this.endpoint = endpoint;
			this.nature = nature;
			this.sourceMissing = sourceMissing;
		}
	}
	
}