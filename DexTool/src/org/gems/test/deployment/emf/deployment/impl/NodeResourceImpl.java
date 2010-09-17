/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.gems.test.deployment.emf.deployment.DeploymentPackage;
import org.gems.test.deployment.emf.deployment.Node;
import org.gems.test.deployment.emf.deployment.NodeResource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.NodeResourceImpl#getResourceValue <em>Resource Value</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.NodeResourceImpl#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeResourceImpl extends ModelObjectImpl implements NodeResource
{
  /**
   * The default value of the '{@link #getResourceValue() <em>Resource Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceValue()
   * @generated
   * @ordered
   */
  protected static final String RESOURCE_VALUE_EDEFAULT = "0";

  /**
   * The cached value of the '{@link #getResourceValue() <em>Resource Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceValue()
   * @generated
   * @ordered
   */
  protected String resourceValue = RESOURCE_VALUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getResources() <em>Resources</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResources()
   * @generated
   * @ordered
   */
  protected Node resources;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NodeResourceImpl()
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
    return DeploymentPackage.Literals.NODE_RESOURCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getResourceValue()
  {
    return resourceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResourceValue(String newResourceValue)
  {
    String oldResourceValue = resourceValue;
    resourceValue = newResourceValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.NODE_RESOURCE__RESOURCE_VALUE, oldResourceValue, resourceValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node getResources()
  {
    if (resources != null && resources.eIsProxy())
    {
      InternalEObject oldResources = (InternalEObject)resources;
      resources = (Node)eResolveProxy(oldResources);
      if (resources != oldResources)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.NODE_RESOURCE__RESOURCES, oldResources, resources));
      }
    }
    return resources;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node basicGetResources()
  {
    return resources;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResources(Node newResources)
  {
    Node oldResources = resources;
    resources = newResources;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.NODE_RESOURCE__RESOURCES, oldResources, resources));
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
      case DeploymentPackage.NODE_RESOURCE__RESOURCE_VALUE:
        return getResourceValue();
      case DeploymentPackage.NODE_RESOURCE__RESOURCES:
        if (resolve) return getResources();
        return basicGetResources();
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
      case DeploymentPackage.NODE_RESOURCE__RESOURCE_VALUE:
        setResourceValue((String)newValue);
        return;
      case DeploymentPackage.NODE_RESOURCE__RESOURCES:
        setResources((Node)newValue);
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
      case DeploymentPackage.NODE_RESOURCE__RESOURCE_VALUE:
        setResourceValue(RESOURCE_VALUE_EDEFAULT);
        return;
      case DeploymentPackage.NODE_RESOURCE__RESOURCES:
        setResources((Node)null);
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
      case DeploymentPackage.NODE_RESOURCE__RESOURCE_VALUE:
        return RESOURCE_VALUE_EDEFAULT == null ? resourceValue != null : !RESOURCE_VALUE_EDEFAULT.equals(resourceValue);
      case DeploymentPackage.NODE_RESOURCE__RESOURCES:
        return resources != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (ResourceValue: ");
    result.append(resourceValue);
    result.append(')');
    return result.toString();
  }

} //NodeResourceImpl
