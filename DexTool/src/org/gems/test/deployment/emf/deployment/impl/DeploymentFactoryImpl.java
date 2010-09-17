/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gems.test.deployment.emf.deployment.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeploymentFactoryImpl extends EFactoryImpl implements DeploymentFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static DeploymentFactory init()
  {
    try
    {
      DeploymentFactory theDeploymentFactory = (DeploymentFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.sf.net/projects/gems/dsml/deployment"); 
      if (theDeploymentFactory != null)
      {
        return theDeploymentFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DeploymentFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeploymentFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case DeploymentPackage.MEMENTO_VALUE: return createMementoValue();
      case DeploymentPackage.MEMENTO: return createMemento();
      case DeploymentPackage.SUBTYPE: return createSubtype();
      case DeploymentPackage.SUBTYPE_LINK: return createSubtypeLink();
      case DeploymentPackage.MODEL_OBJECT: return createModelObject();
      case DeploymentPackage.DEPLOYMENT_PLAN: return createDeploymentPlan();
      case DeploymentPackage.COMPONENT: return createComponent();
      case DeploymentPackage.NODE: return createNode();
      case DeploymentPackage.NODE_RESOURCE: return createNodeResource();
      case DeploymentPackage.COMPONENT_PROPERTY: return createComponentProperty();
      case DeploymentPackage.DEPLOYMENT_CONNECTION: return createDeploymentConnection();
      case DeploymentPackage.ROOT: return createRoot();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case DeploymentPackage.MECHANISM:
        return createMechanismFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case DeploymentPackage.MECHANISM:
        return convertMechanismToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MementoValue createMementoValue()
  {
    MementoValueImpl mementoValue = new MementoValueImpl();
    return mementoValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Memento createMemento()
  {
    MementoImpl memento = new MementoImpl();
    return memento;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Subtype createSubtype()
  {
    SubtypeImpl subtype = new SubtypeImpl();
    return subtype;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SubtypeLink createSubtypeLink()
  {
    SubtypeLinkImpl subtypeLink = new SubtypeLinkImpl();
    return subtypeLink;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelObject createModelObject()
  {
    ModelObjectImpl modelObject = new ModelObjectImpl();
    return modelObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeploymentPlan createDeploymentPlan()
  {
    DeploymentPlanImpl deploymentPlan = new DeploymentPlanImpl();
    return deploymentPlan;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Component createComponent()
  {
    ComponentImpl component = new ComponentImpl();
    return component;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node createNode()
  {
    NodeImpl node = new NodeImpl();
    return node;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeResource createNodeResource()
  {
    NodeResourceImpl nodeResource = new NodeResourceImpl();
    return nodeResource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentProperty createComponentProperty()
  {
    ComponentPropertyImpl componentProperty = new ComponentPropertyImpl();
    return componentProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeploymentConnection createDeploymentConnection()
  {
    DeploymentConnectionImpl deploymentConnection = new DeploymentConnectionImpl();
    return deploymentConnection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Root createRoot()
  {
    RootImpl root = new RootImpl();
    return root;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mechanism createMechanismFromString(EDataType eDataType, String initialValue)
  {
    Mechanism result = Mechanism.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMechanismToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeploymentPackage getDeploymentPackage()
  {
    return (DeploymentPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static DeploymentPackage getPackage()
  {
    return DeploymentPackage.eINSTANCE;
  }

} //DeploymentFactoryImpl
