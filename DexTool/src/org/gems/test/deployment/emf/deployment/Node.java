/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Node#getResourceOf <em>Resource Of</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Node#getDeploymentPlan <em>Deployment Plan</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Node#getHostedComponents <em>Hosted Components</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends ModelObject
{
  /**
   * Returns the value of the '<em><b>Resource Of</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.NodeResource}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource Of</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource Of</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getNode_ResourceOf()
   * @model type="org.gems.test.deployment.emf.deployment.NodeResource" containment="true" upper="2000"
   * @generated
   */
  EList getResourceOf();

  /**
   * Returns the value of the '<em><b>Deployment Plan</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Deployment Plan</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Deployment Plan</em>' reference.
   * @see #setDeploymentPlan(DeploymentPlan)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getNode_DeploymentPlan()
   * @model
   * @generated
   */
  DeploymentPlan getDeploymentPlan();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.Node#getDeploymentPlan <em>Deployment Plan</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Deployment Plan</em>' reference.
   * @see #getDeploymentPlan()
   * @generated
   */
  void setDeploymentPlan(DeploymentPlan value);

  /**
   * Returns the value of the '<em><b>Hosted Components</b></em>' reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.DeploymentConnection}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Hosted Components</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Hosted Components</em>' reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getNode_HostedComponents()
   * @model type="org.gems.test.deployment.emf.deployment.DeploymentConnection" upper="2147483647"
   * @generated
   */
  EList getHostedComponents();

} // Node
