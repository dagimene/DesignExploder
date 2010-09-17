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
 * A representation of the model object '<em><b>Subtype Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.SubtypeLink#getBase <em>Base</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.SubtypeLink#getInstance <em>Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getSubtypeLink()
 * @model
 * @generated
 */
public interface SubtypeLink extends EObject
{
  /**
   * Returns the value of the '<em><b>Base</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Base</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base</em>' reference.
   * @see #setBase(ModelObject)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getSubtypeLink_Base()
   * @model
   * @generated
   */
  ModelObject getBase();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.SubtypeLink#getBase <em>Base</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base</em>' reference.
   * @see #getBase()
   * @generated
   */
  void setBase(ModelObject value);

  /**
   * Returns the value of the '<em><b>Instance</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Instance</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Instance</em>' reference.
   * @see #setInstance(ModelObject)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getSubtypeLink_Instance()
   * @model
   * @generated
   */
  ModelObject getInstance();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.SubtypeLink#getInstance <em>Instance</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Instance</em>' reference.
   * @see #getInstance()
   * @generated
   */
  void setInstance(ModelObject value);

} // SubtypeLink
