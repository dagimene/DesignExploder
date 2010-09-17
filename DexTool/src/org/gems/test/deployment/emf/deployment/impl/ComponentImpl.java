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

import org.gems.test.deployment.emf.deployment.Component;
import org.gems.test.deployment.emf.deployment.ComponentProperty;
import org.gems.test.deployment.emf.deployment.DeploymentConnection;
import org.gems.test.deployment.emf.deployment.DeploymentPackage;
import org.gems.test.deployment.emf.deployment.DeploymentPlan;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ComponentImpl#getCProperties <em>CProperties</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ComponentImpl#getDeploymentPlan <em>Deployment Plan</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ComponentImpl#getHostedBy <em>Hosted By</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends ModelObjectImpl implements Component
{
  /**
   * The cached value of the '{@link #getCProperties() <em>CProperties</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCProperties()
   * @generated
   * @ordered
   */
  protected EList cProperties;

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
   * The cached value of the '{@link #getHostedBy() <em>Hosted By</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHostedBy()
   * @generated
   * @ordered
   */
  protected EList hostedBy;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComponentImpl()
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
    return DeploymentPackage.Literals.COMPONENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getCProperties()
  {
    if (cProperties == null)
    {
      cProperties = new EObjectContainmentEList(ComponentProperty.class, this, DeploymentPackage.COMPONENT__CPROPERTIES);
    }
    return cProperties;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.COMPONENT__DEPLOYMENT_PLAN, oldDeploymentPlan, deploymentPlan));
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
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.COMPONENT__DEPLOYMENT_PLAN, oldDeploymentPlan, deploymentPlan));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getHostedBy()
  {
    if (hostedBy == null)
    {
      hostedBy = new EObjectResolvingEList(DeploymentConnection.class, this, DeploymentPackage.COMPONENT__HOSTED_BY);
    }
    return hostedBy;
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
      case DeploymentPackage.COMPONENT__CPROPERTIES:
        return ((InternalEList)getCProperties()).basicRemove(otherEnd, msgs);
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
      case DeploymentPackage.COMPONENT__CPROPERTIES:
        return getCProperties();
      case DeploymentPackage.COMPONENT__DEPLOYMENT_PLAN:
        if (resolve) return getDeploymentPlan();
        return basicGetDeploymentPlan();
      case DeploymentPackage.COMPONENT__HOSTED_BY:
        return getHostedBy();
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
      case DeploymentPackage.COMPONENT__CPROPERTIES:
        getCProperties().clear();
        getCProperties().addAll((Collection)newValue);
        return;
      case DeploymentPackage.COMPONENT__DEPLOYMENT_PLAN:
        setDeploymentPlan((DeploymentPlan)newValue);
        return;
      case DeploymentPackage.COMPONENT__HOSTED_BY:
        getHostedBy().clear();
        getHostedBy().addAll((Collection)newValue);
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
      case DeploymentPackage.COMPONENT__CPROPERTIES:
        getCProperties().clear();
        return;
      case DeploymentPackage.COMPONENT__DEPLOYMENT_PLAN:
        setDeploymentPlan((DeploymentPlan)null);
        return;
      case DeploymentPackage.COMPONENT__HOSTED_BY:
        getHostedBy().clear();
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
      case DeploymentPackage.COMPONENT__CPROPERTIES:
        return cProperties != null && !cProperties.isEmpty();
      case DeploymentPackage.COMPONENT__DEPLOYMENT_PLAN:
        return deploymentPlan != null;
      case DeploymentPackage.COMPONENT__HOSTED_BY:
        return hostedBy != null && !hostedBy.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ComponentImpl
