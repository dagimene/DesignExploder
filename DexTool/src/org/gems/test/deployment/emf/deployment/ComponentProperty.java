/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ComponentProperty#getCPropertyValue <em>CProperty Value</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ComponentProperty#getCPropertyOf <em>CProperty Of</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getComponentProperty()
 * @model
 * @generated
 */
public interface ComponentProperty extends ModelObject
{
  /**
   * Returns the value of the '<em><b>CProperty Value</b></em>' attribute.
   * The default value is <code>"0"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>CProperty Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>CProperty Value</em>' attribute.
   * @see #setCPropertyValue(String)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getComponentProperty_CPropertyValue()
   * @model default="0"
   * @generated
   */
  String getCPropertyValue();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ComponentProperty#getCPropertyValue <em>CProperty Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>CProperty Value</em>' attribute.
   * @see #getCPropertyValue()
   * @generated
   */
  void setCPropertyValue(String value);

  /**
   * Returns the value of the '<em><b>CProperty Of</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>CProperty Of</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>CProperty Of</em>' reference.
   * @see #setCPropertyOf(Component)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getComponentProperty_CPropertyOf()
   * @model
   * @generated
   */
  Component getCPropertyOf();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ComponentProperty#getCPropertyOf <em>CProperty Of</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>CProperty Of</em>' reference.
   * @see #getCPropertyOf()
   * @generated
   */
  void setCPropertyOf(Component value);

} // ComponentProperty
