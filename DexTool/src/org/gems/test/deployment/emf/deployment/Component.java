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
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Component#getCProperties <em>CProperties</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Component#getDeploymentPlan <em>Deployment Plan</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Component#getHostedBy <em>Hosted By</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends ModelObject
{
  /**
   * Returns the value of the '<em><b>CProperties</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.ComponentProperty}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>CProperties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>CProperties</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getComponent_CProperties()
   * @model type="org.gems.test.deployment.emf.deployment.ComponentProperty" containment="true" upper="2000"
   * @generated
   */
  EList getCProperties();

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
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getComponent_DeploymentPlan()
   * @model
   * @generated
   */
  DeploymentPlan getDeploymentPlan();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.Component#getDeploymentPlan <em>Deployment Plan</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Deployment Plan</em>' reference.
   * @see #getDeploymentPlan()
   * @generated
   */
  void setDeploymentPlan(DeploymentPlan value);

  /**
   * Returns the value of the '<em><b>Hosted By</b></em>' reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.DeploymentConnection}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Hosted By</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Hosted By</em>' reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getComponent_HostedBy()
   * @model type="org.gems.test.deployment.emf.deployment.DeploymentConnection" upper="2147483647"
   * @generated
   */
  EList getHostedBy();

} // Component
