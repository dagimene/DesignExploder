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
 * A representation of the model object '<em><b>Subtype</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Subtype#getName <em>Name</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Subtype#getBase <em>Base</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Subtype#getInstances <em>Instances</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.Subtype#getLinks <em>Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getSubtype()
 * @model
 * @generated
 */
public interface Subtype extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * The default value is <code>"AnonymousSubtype"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getSubtype_Name()
   * @model default="AnonymousSubtype"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.Subtype#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

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
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getSubtype_Base()
   * @model
   * @generated
   */
  ModelObject getBase();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.Subtype#getBase <em>Base</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base</em>' reference.
   * @see #getBase()
   * @generated
   */
  void setBase(ModelObject value);

  /**
   * Returns the value of the '<em><b>Instances</b></em>' reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.ModelObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Instances</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Instances</em>' reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getSubtype_Instances()
   * @model type="org.gems.test.deployment.emf.deployment.ModelObject" upper="2000"
   * @generated
   */
  EList getInstances();

  /**
   * Returns the value of the '<em><b>Links</b></em>' containment reference list.
   * The list contents are of type {@link org.gems.test.deployment.emf.deployment.SubtypeLink}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Links</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Links</em>' containment reference list.
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getSubtype_Links()
   * @model type="org.gems.test.deployment.emf.deployment.SubtypeLink" containment="true" upper="20000"
   * @generated
   */
  EList getLinks();

} // Subtype
