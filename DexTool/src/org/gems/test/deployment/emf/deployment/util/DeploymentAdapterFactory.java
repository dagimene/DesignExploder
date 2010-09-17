/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.gems.test.deployment.emf.deployment.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage
 * @generated
 */
public class DeploymentAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static DeploymentPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeploymentAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = DeploymentPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DeploymentSwitch modelSwitch =
    new DeploymentSwitch()
    {
      public Object caseMementoValue(MementoValue object)
      {
        return createMementoValueAdapter();
      }
      public Object caseMemento(Memento object)
      {
        return createMementoAdapter();
      }
      public Object caseSubtype(Subtype object)
      {
        return createSubtypeAdapter();
      }
      public Object caseSubtypeLink(SubtypeLink object)
      {
        return createSubtypeLinkAdapter();
      }
      public Object caseModelObject(ModelObject object)
      {
        return createModelObjectAdapter();
      }
      public Object caseDeploymentPlan(DeploymentPlan object)
      {
        return createDeploymentPlanAdapter();
      }
      public Object caseComponent(Component object)
      {
        return createComponentAdapter();
      }
      public Object caseNode(Node object)
      {
        return createNodeAdapter();
      }
      public Object caseNodeResource(NodeResource object)
      {
        return createNodeResourceAdapter();
      }
      public Object caseComponentProperty(ComponentProperty object)
      {
        return createComponentPropertyAdapter();
      }
      public Object caseDeploymentConnection(DeploymentConnection object)
      {
        return createDeploymentConnectionAdapter();
      }
      public Object caseRoot(Root object)
      {
        return createRootAdapter();
      }
      public Object defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  public Adapter createAdapter(Notifier target)
  {
    return (Adapter)modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.MementoValue <em>Memento Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.MementoValue
   * @generated
   */
  public Adapter createMementoValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.Memento <em>Memento</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.Memento
   * @generated
   */
  public Adapter createMementoAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.Subtype <em>Subtype</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.Subtype
   * @generated
   */
  public Adapter createSubtypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.SubtypeLink <em>Subtype Link</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.SubtypeLink
   * @generated
   */
  public Adapter createSubtypeLinkAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.ModelObject <em>Model Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.ModelObject
   * @generated
   */
  public Adapter createModelObjectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.DeploymentPlan <em>Plan</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPlan
   * @generated
   */
  public Adapter createDeploymentPlanAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.Component <em>Component</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.Component
   * @generated
   */
  public Adapter createComponentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.Node <em>Node</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.Node
   * @generated
   */
  public Adapter createNodeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.NodeResource <em>Node Resource</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.NodeResource
   * @generated
   */
  public Adapter createNodeResourceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.ComponentProperty <em>Component Property</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.ComponentProperty
   * @generated
   */
  public Adapter createComponentPropertyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.DeploymentConnection <em>Connection</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.DeploymentConnection
   * @generated
   */
  public Adapter createDeploymentConnectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.gems.test.deployment.emf.deployment.Root <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.gems.test.deployment.emf.deployment.Root
   * @generated
   */
  public Adapter createRootAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //DeploymentAdapterFactory
