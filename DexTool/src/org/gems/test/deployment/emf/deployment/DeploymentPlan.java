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
 * A representation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.DeploymentPlan#getComponents <em>Components</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.DeploymentPlan#getNodes <em>Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getDeploymentPlan()
 * @model
 * @generated
 */
public interface DeploymentPlan extends ModelObject
{
  /**
   * Returns the value of the '<em><b>Components</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.Component}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Components</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Components</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getDeploymentPlan_Components()
   * @model type="org.gems.test.deployment.emf.deployment.Component" containment="true" upper="2000"
   * @generated
   */
  EList getComponents();

  /**
   * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.Node}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nodes</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getDeploymentPlan_Nodes()
   * @model type="org.gems.test.deployment.emf.deployment.Node" containment="true" upper="2000"
   * @generated
   */
  EList getNodes();

} // DeploymentPlan
