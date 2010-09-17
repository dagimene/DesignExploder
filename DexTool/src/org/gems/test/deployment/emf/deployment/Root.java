/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getMementos <em>Mementos</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getSubtypes <em>Subtypes</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getRealRoot <em>Real Root</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getDeploymentPlan <em>Deployment Plan</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getComponent <em>Component</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getNode <em>Node</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getNodeResource <em>Node Resource</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getComponentProperty <em>Component Property</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Root#getDeploymentConnection <em>Deployment Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends EObject
{
  /**
   * Returns the value of the '<em><b>Mementos</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.Memento}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mementos</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mementos</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_Mementos()
   * @model type="org.gems.test.deployment.emf.deployment.Memento" containment="true" upper="2000"
   * @generated
   */
  EList getMementos();

  /**
   * Returns the value of the '<em><b>Subtypes</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.Subtype}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subtypes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subtypes</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_Subtypes()
   * @model type="org.gems.test.deployment.emf.deployment.Subtype" containment="true" upper="2000"
   * @generated
   */
  EList getSubtypes();

  /**
   * Returns the value of the '<em><b>Real Root</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Real Root</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Real Root</em>' containment reference.
   * @see #setRealRoot(DeploymentPlan)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_RealRoot()
   * @model containment="true"
   * @generated
   */
  DeploymentPlan getRealRoot();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.Root#getRealRoot <em>Real Root</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Real Root</em>' containment reference.
   * @see #getRealRoot()
   * @generated
   */
  void setRealRoot(DeploymentPlan value);

  /**
   * Returns the value of the '<em><b>Deployment Plan</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.DeploymentPlan}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Deployment Plan</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Deployment Plan</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_DeploymentPlan()
   * @model type="org.gems.test.deployment.emf.deployment.DeploymentPlan" containment="true" upper="2000"
   * @generated
   */
  EList getDeploymentPlan();

  /**
   * Returns the value of the '<em><b>Component</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.Component}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_Component()
   * @model type="org.gems.test.deployment.emf.deployment.Component" containment="true" upper="2000"
   * @generated
   */
  EList getComponent();

  /**
   * Returns the value of the '<em><b>Node</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.Node}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Node</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_Node()
   * @model type="org.gems.test.deployment.emf.deployment.Node" containment="true" upper="2000"
   * @generated
   */
  EList getNode();

  /**
   * Returns the value of the '<em><b>Node Resource</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.NodeResource}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Node Resource</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Node Resource</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_NodeResource()
   * @model type="org.gems.test.deployment.emf.deployment.NodeResource" containment="true" upper="2000"
   * @generated
   */
  EList getNodeResource();

  /**
   * Returns the value of the '<em><b>Component Property</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.ComponentProperty}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component Property</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component Property</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_ComponentProperty()
   * @model type="org.gems.test.deployment.emf.deployment.ComponentProperty" containment="true" upper="2000"
   * @generated
   */
  EList getComponentProperty();

  /**
   * Returns the value of the '<em><b>Deployment Connection</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.DeploymentConnection}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Deployment Connection</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Deployment Connection</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getRoot_DeploymentConnection()
   * @model type="org.gems.test.deployment.emf.deployment.DeploymentConnection" containment="true" upper="2000"
   * @generated
   */
  EList getDeploymentConnection();

} // Root
