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

import org.gems.test.deployment.emf.deployment.Component;
import org.gems.test.deployment.emf.deployment.ComponentProperty;
import org.gems.test.deployment.emf.deployment.DeploymentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ComponentPropertyImpl#getCPropertyValue <em>CProperty Value</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ComponentPropertyImpl#getCPropertyOf <em>CProperty Of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentPropertyImpl extends ModelObjectImpl implements ComponentProperty
{
  /**
   * The default value of the '{@link #getCPropertyValue() <em>CProperty Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCPropertyValue()
   * @generated
   * @ordered
   */
  protected static final String CPROPERTY_VALUE_EDEFAULT = "0";

  /**
   * The cached value of the '{@link #getCPropertyValue() <em>CProperty Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCPropertyValue()
   * @generated
   * @ordered
   */
  protected String cPropertyValue = CPROPERTY_VALUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getCPropertyOf() <em>CProperty Of</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCPropertyOf()
   * @generated
   * @ordered
   */
  protected Component cPropertyOf;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComponentPropertyImpl()
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
    return DeploymentPackage.Literals.COMPONENT_PROPERTY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCPropertyValue()
  {
    return cPropertyValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCPropertyValue(String newCPropertyValue)
  {
    String oldCPropertyValue = cPropertyValue;
    cPropertyValue = newCPropertyValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_VALUE, oldCPropertyValue, cPropertyValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Component getCPropertyOf()
  {
    if (cPropertyOf != null && cPropertyOf.eIsProxy())
    {
      InternalEObject oldCPropertyOf = (InternalEObject)cPropertyOf;
      cPropertyOf = (Component)eResolveProxy(oldCPropertyOf);
      if (cPropertyOf != oldCPropertyOf)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_OF, oldCPropertyOf, cPropertyOf));
      }
    }
    return cPropertyOf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Component basicGetCPropertyOf()
  {
    return cPropertyOf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCPropertyOf(Component newCPropertyOf)
  {
    Component oldCPropertyOf = cPropertyOf;
    cPropertyOf = newCPropertyOf;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_OF, oldCPropertyOf, cPropertyOf));
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
      case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_VALUE:
        return getCPropertyValue();
      case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_OF:
        if (resolve) return getCPropertyOf();
        return basicGetCPropertyOf();
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
      case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_VALUE:
        setCPropertyValue((String)newValue);
        return;
      case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_OF:
        setCPropertyOf((Component)newValue);
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
      case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_VALUE:
        setCPropertyValue(CPROPERTY_VALUE_EDEFAULT);
        return;
      case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_OF:
        setCPropertyOf((Component)null);
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
      case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_VALUE:
        return CPROPERTY_VALUE_EDEFAULT == null ? cPropertyValue != null : !CPROPERTY_VALUE_EDEFAULT.equals(cPropertyValue);
      case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_OF:
        return cPropertyOf != null;
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
    result.append(" (CPropertyValue: ");
    result.append(cPropertyValue);
    result.append(')');
    return result.toString();
  }

} //ComponentPropertyImpl
