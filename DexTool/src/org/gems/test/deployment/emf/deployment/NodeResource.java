/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.NodeResource#getResourceValue <em>Resource Value</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.NodeResource#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getNodeResource()
 * @model
 * @generated
 */
public interface NodeResource extends ModelObject
{
  /**
   * Returns the value of the '<em><b>Resource Value</b></em>' attribute.
   * The default value is <code>"0"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource Value</em>' attribute.
   * @see #setResourceValue(String)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getNodeResource_ResourceValue()
   * @model default="0"
   * @generated
   */
  String getResourceValue();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.NodeResource#getResourceValue <em>Resource Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resource Value</em>' attribute.
   * @see #getResourceValue()
   * @generated
   */
  void setResourceValue(String value);

  /**
   * Returns the value of the '<em><b>Resources</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resources</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resources</em>' reference.
   * @see #setResources(Node)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getNodeResource_Resources()
   * @model
   * @generated
   */
  Node getResources();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.NodeResource#getResources <em>Resources</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resources</em>' reference.
   * @see #getResources()
   * @generated
   */
  void setResources(Node value);

} // NodeResource
