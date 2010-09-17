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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.gems.test.deployment.emf.deployment.Component;
import org.gems.test.deployment.emf.deployment.ComponentProperty;
import org.gems.test.deployment.emf.deployment.DeploymentConnection;
import org.gems.test.deployment.emf.deployment.DeploymentPackage;
import org.gems.test.deployment.emf.deployment.DeploymentPlan;
import org.gems.test.deployment.emf.deployment.Memento;
import org.gems.test.deployment.emf.deployment.Node;
import org.gems.test.deployment.emf.deployment.NodeResource;
import org.gems.test.deployment.emf.deployment.Root;
import org.gems.test.deployment.emf.deployment.Subtype;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getMementos <em>Mementos</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getSubtypes <em>Subtypes</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getRealRoot <em>Real Root</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getDeploymentPlan <em>Deployment Plan</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getNodeResource <em>Node Resource</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getComponentProperty <em>Component Property</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.RootImpl#getDeploymentConnection <em>Deployment Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RootImpl extends EObjectImpl implements Root
{
  /**
   * The cached value of the '{@link #getMementos() <em>Mementos</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMementos()
   * @generated
   * @ordered
   */
  protected EList mementos;

  /**
   * The cached value of the '{@link #getSubtypes() <em>Subtypes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubtypes()
   * @generated
   * @ordered
   */
  protected EList subtypes;

  /**
   * The cached value of the '{@link #getRealRoot() <em>Real Root</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRealRoot()
   * @generated
   * @ordered
   */
  protected DeploymentPlan realRoot;

  /**
   * The cached value of the '{@link #getDeploymentPlan() <em>Deployment Plan</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeploymentPlan()
   * @generated
   * @ordered
   */
  protected EList deploymentPlan;

  /**
   * The cached value of the '{@link #getComponent() <em>Component</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponent()
   * @generated
   * @ordered
   */
  protected EList component;

  /**
   * The cached value of the '{@link #getNode() <em>Node</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNode()
   * @generated
   * @ordered
   */
  protected EList node;

  /**
   * The cached value of the '{@link #getNodeResource() <em>Node Resource</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNodeResource()
   * @generated
   * @ordered
   */
  protected EList nodeResource;

  /**
   * The cached value of the '{@link #getComponentProperty() <em>Component Property</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponentProperty()
   * @generated
   * @ordered
   */
  protected EList componentProperty;

  /**
   * The cached value of the '{@link #getDeploymentConnection() <em>Deployment Connection</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeploymentConnection()
   * @generated
   * @ordered
   */
  protected EList deploymentConnection;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RootImpl()
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
    return DeploymentPackage.Literals.ROOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getMementos()
  {
    if (mementos == null)
    {
      mementos = new EObjectContainmentEList(Memento.class, this, DeploymentPackage.ROOT__MEMENTOS);
    }
    return mementos;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getSubtypes()
  {
    if (subtypes == null)
    {
      subtypes = new EObjectContainmentEList(Subtype.class, this, DeploymentPackage.ROOT__SUBTYPES);
    }
    return subtypes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeploymentPlan getRealRoot()
  {
    return realRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRealRoot(DeploymentPlan newRealRoot, NotificationChain msgs)
  {
    DeploymentPlan oldRealRoot = realRoot;
    realRoot = newRealRoot;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DeploymentPackage.ROOT__REAL_ROOT, oldRealRoot, newRealRoot);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRealRoot(DeploymentPlan newRealRoot)
  {
    if (newRealRoot != realRoot)
    {
      NotificationChain msgs = null;
      if (realRoot != null)
        msgs = ((InternalEObject)realRoot).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DeploymentPackage.ROOT__REAL_ROOT, null, msgs);
      if (newRealRoot != null)
        msgs = ((InternalEObject)newRealRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DeploymentPackage.ROOT__REAL_ROOT, null, msgs);
      msgs = basicSetRealRoot(newRealRoot, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.ROOT__REAL_ROOT, newRealRoot, newRealRoot));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getDeploymentPlan()
  {
    if (deploymentPlan == null)
    {
      deploymentPlan = new EObjectContainmentEList(DeploymentPlan.class, this, DeploymentPackage.ROOT__DEPLOYMENT_PLAN);
    }
    return deploymentPlan;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getComponent()
  {
    if (component == null)
    {
      component = new EObjectContainmentEList(Component.class, this, DeploymentPackage.ROOT__COMPONENT);
    }
    return component;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getNode()
  {
    if (node == null)
    {
      node = new EObjectContainmentEList(Node.class, this, DeploymentPackage.ROOT__NODE);
    }
    return node;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getNodeResource()
  {
    if (nodeResource == null)
    {
      nodeResource = new EObjectContainmentEList(NodeResource.class, this, DeploymentPackage.ROOT__NODE_RESOURCE);
    }
    return nodeResource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getComponentProperty()
  {
    if (componentProperty == null)
    {
      componentProperty = new EObjectContainmentEList(ComponentProperty.class, this, DeploymentPackage.ROOT__COMPONENT_PROPERTY);
    }
    return componentProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getDeploymentConnection()
  {
    if (deploymentConnection == null)
    {
      deploymentConnection = new EObjectContainmentEList(DeploymentConnection.class, this, DeploymentPackage.ROOT__DEPLOYMENT_CONNECTION);
    }
    return deploymentConnection;
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
      case DeploymentPackage.ROOT__MEMENTOS:
        return ((InternalEList)getMementos()).basicRemove(otherEnd, msgs);
      case DeploymentPackage.ROOT__SUBTYPES:
        return ((InternalEList)getSubtypes()).basicRemove(otherEnd, msgs);
      case DeploymentPackage.ROOT__REAL_ROOT:
        return basicSetRealRoot(null, msgs);
      case DeploymentPackage.ROOT__DEPLOYMENT_PLAN:
        return ((InternalEList)getDeploymentPlan()).basicRemove(otherEnd, msgs);
      case DeploymentPackage.ROOT__COMPONENT:
        return ((InternalEList)getComponent()).basicRemove(otherEnd, msgs);
      case DeploymentPackage.ROOT__NODE:
        return ((InternalEList)getNode()).basicRemove(otherEnd, msgs);
      case DeploymentPackage.ROOT__NODE_RESOURCE:
        return ((InternalEList)getNodeResource()).basicRemove(otherEnd, msgs);
      case DeploymentPackage.ROOT__COMPONENT_PROPERTY:
        return ((InternalEList)getComponentProperty()).basicRemove(otherEnd, msgs);
      case DeploymentPackage.ROOT__DEPLOYMENT_CONNECTION:
        return ((InternalEList)getDeploymentConnection()).basicRemove(otherEnd, msgs);
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
      case DeploymentPackage.ROOT__MEMENTOS:
        return getMementos();
      case DeploymentPackage.ROOT__SUBTYPES:
        return getSubtypes();
      case DeploymentPackage.ROOT__REAL_ROOT:
        return getRealRoot();
      case DeploymentPackage.ROOT__DEPLOYMENT_PLAN:
        return getDeploymentPlan();
      case DeploymentPackage.ROOT__COMPONENT:
        return getComponent();
      case DeploymentPackage.ROOT__NODE:
        return getNode();
      case DeploymentPackage.ROOT__NODE_RESOURCE:
        return getNodeResource();
      case DeploymentPackage.ROOT__COMPONENT_PROPERTY:
        return getComponentProperty();
      case DeploymentPackage.ROOT__DEPLOYMENT_CONNECTION:
        return getDeploymentConnection();
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
      case DeploymentPackage.ROOT__MEMENTOS:
        getMementos().clear();
        getMementos().addAll((Collection)newValue);
        return;
      case DeploymentPackage.ROOT__SUBTYPES:
        getSubtypes().clear();
        getSubtypes().addAll((Collection)newValue);
        return;
      case DeploymentPackage.ROOT__REAL_ROOT:
        setRealRoot((DeploymentPlan)newValue);
        return;
      case DeploymentPackage.ROOT__DEPLOYMENT_PLAN:
        getDeploymentPlan().clear();
        getDeploymentPlan().addAll((Collection)newValue);
        return;
      case DeploymentPackage.ROOT__COMPONENT:
        getComponent().clear();
        getComponent().addAll((Collection)newValue);
        return;
      case DeploymentPackage.ROOT__NODE:
        getNode().clear();
        getNode().addAll((Collection)newValue);
        return;
      case DeploymentPackage.ROOT__NODE_RESOURCE:
        getNodeResource().clear();
        getNodeResource().addAll((Collection)newValue);
        return;
      case DeploymentPackage.ROOT__COMPONENT_PROPERTY:
        getComponentProperty().clear();
        getComponentProperty().addAll((Collection)newValue);
        return;
      case DeploymentPackage.ROOT__DEPLOYMENT_CONNECTION:
        getDeploymentConnection().clear();
        getDeploymentConnection().addAll((Collection)newValue);
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
      case DeploymentPackage.ROOT__MEMENTOS:
        getMementos().clear();
        return;
      case DeploymentPackage.ROOT__SUBTYPES:
        getSubtypes().clear();
        return;
      case DeploymentPackage.ROOT__REAL_ROOT:
        setRealRoot((DeploymentPlan)null);
        return;
      case DeploymentPackage.ROOT__DEPLOYMENT_PLAN:
        getDeploymentPlan().clear();
        return;
      case DeploymentPackage.ROOT__COMPONENT:
        getComponent().clear();
        return;
      case DeploymentPackage.ROOT__NODE:
        getNode().clear();
        return;
      case DeploymentPackage.ROOT__NODE_RESOURCE:
        getNodeResource().clear();
        return;
      case DeploymentPackage.ROOT__COMPONENT_PROPERTY:
        getComponentProperty().clear();
        return;
      case DeploymentPackage.ROOT__DEPLOYMENT_CONNECTION:
        getDeploymentConnection().clear();
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
      case DeploymentPackage.ROOT__MEMENTOS:
        return mementos != null && !mementos.isEmpty();
      case DeploymentPackage.ROOT__SUBTYPES:
        return subtypes != null && !subtypes.isEmpty();
      case DeploymentPackage.ROOT__REAL_ROOT:
        return realRoot != null;
      case DeploymentPackage.ROOT__DEPLOYMENT_PLAN:
        return deploymentPlan != null && !deploymentPlan.isEmpty();
      case DeploymentPackage.ROOT__COMPONENT:
        return component != null && !component.isEmpty();
      case DeploymentPackage.ROOT__NODE:
        return node != null && !node.isEmpty();
      case DeploymentPackage.ROOT__NODE_RESOURCE:
        return nodeResource != null && !nodeResource.isEmpty();
      case DeploymentPackage.ROOT__COMPONENT_PROPERTY:
        return componentProperty != null && !componentProperty.isEmpty();
      case DeploymentPackage.ROOT__DEPLOYMENT_CONNECTION:
        return deploymentConnection != null && !deploymentConnection.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //RootImpl
