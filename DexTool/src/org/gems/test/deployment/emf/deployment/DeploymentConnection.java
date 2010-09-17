/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.DeploymentConnection#getSource <em>Source</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.DeploymentConnection#getTarget <em>Target</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.DeploymentConnection#getMechanism <em>Mechanism</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getDeploymentConnection()
 * @model
 * @generated
 */
public interface DeploymentConnection extends EObject
{
  /**
   * Returns the value of the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(Component)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getDeploymentConnection_Source()
   * @model
   * @generated
   */
  Component getSource();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.DeploymentConnection#getSource <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */
  void setSource(Component value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(Node)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getDeploymentConnection_Target()
   * @model
   * @generated
   */
  Node getTarget();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.DeploymentConnection#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(Node value);

  /**
   * Returns the value of the '<em><b>Mechanism</b></em>' attribute.
   * The default value is <code>"manual"</code>.
   * The literals are from the enumeration {@link org.gems.test.deployment.emf.deployment.Mechanism}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mechanism</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mechanism</em>' attribute.
   * @see org.gems.test.deployment.emf.deployment.Mechanism
   * @see #setMechanism(Mechanism)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getDeploymentConnection_Mechanism()
   * @model default="manual" dataType="org.gems.test.deployment.emf.deployment.Mechanism"
   * @generated
   */
  Mechanism getMechanism();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.DeploymentConnection#getMechanism <em>Mechanism</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mechanism</em>' attribute.
   * @see org.gems.test.deployment.emf.deployment.Mechanism
   * @see #getMechanism()
   * @generated
   */
  void setMechanism(Mechanism value);

} // DeploymentConnection
