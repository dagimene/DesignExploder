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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.gems.test.deployment.emf.deployment.Component;
import org.gems.test.deployment.emf.deployment.DeploymentConnection;
import org.gems.test.deployment.emf.deployment.DeploymentPackage;
import org.gems.test.deployment.emf.deployment.Mechanism;
import org.gems.test.deployment.emf.deployment.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.DeploymentConnectionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.DeploymentConnectionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.DeploymentConnectionImpl#getMechanism <em>Mechanism</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeploymentConnectionImpl extends EObjectImpl implements DeploymentConnection
{
  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected Component source;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected Node target;

  /**
   * The default value of the '{@link #getMechanism() <em>Mechanism</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMechanism()
   * @generated
   * @ordered
   */
  protected static final Mechanism MECHANISM_EDEFAULT = Mechanism.MANUAL_LITERAL;

  /**
   * The cached value of the '{@link #getMechanism() <em>Mechanism</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMechanism()
   * @generated
   * @ordered
   */
  protected Mechanism mechanism = MECHANISM_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DeploymentConnectionImpl()
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
    return DeploymentPackage.Literals.DEPLOYMENT_CONNECTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Component getSource()
  {
    if (source != null && source.eIsProxy())
    {
      InternalEObject oldSource = (InternalEObject)source;
      source = (Component)eResolveProxy(oldSource);
      if (source != oldSource)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.DEPLOYMENT_CONNECTION__SOURCE, oldSource, source));
      }
    }
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Component basicGetSource()
  {
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSource(Component newSource)
  {
    Component oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.DEPLOYMENT_CONNECTION__SOURCE, oldSource, source));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node getTarget()
  {
    if (target != null && target.eIsProxy())
    {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (Node)eResolveProxy(oldTarget);
      if (target != oldTarget)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.DEPLOYMENT_CONNECTION__TARGET, oldTarget, target));
      }
    }
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node basicGetTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(Node newTarget)
  {
    Node oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.DEPLOYMENT_CONNECTION__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mechanism getMechanism()
  {
    return mechanism;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMechanism(Mechanism newMechanism)
  {
    Mechanism oldMechanism = mechanism;
    mechanism = newMechanism == null ? MECHANISM_EDEFAULT : newMechanism;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.DEPLOYMENT_CONNECTION__MECHANISM, oldMechanism, mechanism));
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
      case DeploymentPackage.DEPLOYMENT_CONNECTION__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case DeploymentPackage.DEPLOYMENT_CONNECTION__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case DeploymentPackage.DEPLOYMENT_CONNECTION__MECHANISM:
        return getMechanism();
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
      case DeploymentPackage.DEPLOYMENT_CONNECTION__SOURCE:
        setSource((Component)newValue);
        return;
      case DeploymentPackage.DEPLOYMENT_CONNECTION__TARGET:
        setTarget((Node)newValue);
        return;
      case DeploymentPackage.DEPLOYMENT_CONNECTION__MECHANISM:
        setMechanism((Mechanism)newValue);
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
      case DeploymentPackage.DEPLOYMENT_CONNECTION__SOURCE:
        setSource((Component)null);
        return;
      case DeploymentPackage.DEPLOYMENT_CONNECTION__TARGET:
        setTarget((Node)null);
        return;
      case DeploymentPackage.DEPLOYMENT_CONNECTION__MECHANISM:
        setMechanism(MECHANISM_EDEFAULT);
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
      case DeploymentPackage.DEPLOYMENT_CONNECTION__SOURCE:
        return source != null;
      case DeploymentPackage.DEPLOYMENT_CONNECTION__TARGET:
        return target != null;
      case DeploymentPackage.DEPLOYMENT_CONNECTION__MECHANISM:
        return mechanism != MECHANISM_EDEFAULT;
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
    result.append(" (Mechanism: ");
    result.append(mechanism);
    result.append(')');
    return result.toString();
  }

} //DeploymentConnectionImpl
