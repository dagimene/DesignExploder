/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.gems.test.deployment.emf.deployment.DeploymentConnection;
import org.gems.test.deployment.emf.deployment.DeploymentPackage;
import org.gems.test.deployment.emf.deployment.DeploymentPlan;
import org.gems.test.deployment.emf.deployment.Node;
import org.gems.test.deployment.emf.deployment.NodeResource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.NodeImpl#getResourceOf <em>Resource Of</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.NodeImpl#getDeploymentPlan <em>Deployment Plan</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.NodeImpl#getHostedComponents <em>Hosted Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends ModelObjectImpl implements Node
{
  /**
   * The cached value of the '{@link #getResourceOf() <em>Resource Of</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceOf()
   * @generated
   * @ordered
   */
  protected EList resourceOf;

  /**
   * The cached value of the '{@link #getDeploymentPlan() <em>Deployment Plan</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeploymentPlan()
   * @generated
   * @ordered
   */
  protected DeploymentPlan deploymentPlan;

  /**
   * The cached value of the '{@link #getHostedComponents() <em>Hosted Components</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHostedComponents()
   * @generated
   * @ordered
   */
  protected EList hostedComponents;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NodeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return DeploymentPackage.Literals.NODE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getResourceOf()
  {
    if (resourceOf == null)
    {
      resourceOf = new EObjectContainmentEList(NodeResource.class, this, DeploymentPackage.NODE__RESOURCE_OF);
    }
    return resourceOf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeploymentPlan getDeploymentPlan()
  {
    if (deploymentPlan != null && deploymentPlan.eIsProxy())
    {
      InternalEObject oldDeploymentPlan = (InternalEObject)deploymentPlan;
      deploymentPlan = (DeploymentPlan)eResolveProxy(oldDeploymentPlan);
      if (deploymentPlan != oldDeploymentPlan)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.NODE__DEPLOYMENT_PLAN, oldDeploymentPlan, deploymentPlan));
      }
    }
    return deploymentPlan;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeploymentPlan basicGetDeploymentPlan()
  {
    return deploymentPlan;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDeploymentPlan(DeploymentPlan newDeploymentPlan)
  {
    DeploymentPlan oldDeploymentPlan = deploymentPlan;
    deploymentPlan = newDeploymentPlan;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.NODE__DEPLOYMENT_PLAN, oldDeploymentPlan, deploymentPlan));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getHostedComponents()
  {
    if (hostedComponents == null)
    {
      hostedComponents = new EObjectResolvingEList(DeploymentConnection.class, this, DeploymentPackage.NODE__HOSTED_COMPONENTS);
    }
    return hostedComponents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case DeploymentPackage.NODE__RESOURCE_OF:
        return ((InternalEList)getResourceOf()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case DeploymentPackage.NODE__RESOURCE_OF:
        return getResourceOf();
      case DeploymentPackage.NODE__DEPLOYMENT_PLAN:
        if (resolve) return getDeploymentPlan();
        return basicGetDeploymentPlan();
      case DeploymentPackage.NODE__HOSTED_COMPONENTS:
        return getHostedComponents();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DeploymentPackage.NODE__RESOURCE_OF:
        getResourceOf().clear();
        getResourceOf().addAll((Collection)newValue);
        return;
      case DeploymentPackage.NODE__DEPLOYMENT_PLAN:
        setDeploymentPlan((DeploymentPlan)newValue);
        return;
      case DeploymentPackage.NODE__HOSTED_COMPONENTS:
        getHostedComponents().clear();
        getHostedComponents().addAll((Collection)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case DeploymentPackage.NODE__RESOURCE_OF:
        getResourceOf().clear();
        return;
      case DeploymentPackage.NODE__DEPLOYMENT_PLAN:
        setDeploymentPlan((DeploymentPlan)null);
        return;
      case DeploymentPackage.NODE__HOSTED_COMPONENTS:
        getHostedComponents().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case DeploymentPackage.NODE__RESOURCE_OF:
        return resourceOf != null && !resourceOf.isEmpty();
      case DeploymentPackage.NODE__DEPLOYMENT_PLAN:
        return deploymentPlan != null;
      case DeploymentPackage.NODE__HOSTED_COMPONENTS:
        return hostedComponents != null && !hostedComponents.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //NodeImpl
