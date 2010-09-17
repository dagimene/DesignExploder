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
 * A representation of the model object '<em><b>Model Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getName <em>Name</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getId <em>Id</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getX <em>X</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getY <em>Y</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getWidth <em>Width</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getHeight <em>Height</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getExpandedWidth <em>Expanded Width</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getExpandedHeight <em>Expanded Height</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#isExpanded <em>Expanded</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#isSubtype <em>Subtype</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#isVisible <em>Visible</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getModelLinkTarget <em>Model Link Target</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.ModelObject#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject()
 * @model
 * @generated
 */
public interface ModelObject extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * The default value is <code>"0"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Name()
   * @model default="0"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * The default value is <code>"0"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Id()
   * @model default="0"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>X</b></em>' attribute.
   * The default value is <code>"0"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>X</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>X</em>' attribute.
   * @see #setX(int)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_X()
   * @model default="0"
   * @generated
   */
  int getX();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getX <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>X</em>' attribute.
   * @see #getX()
   * @generated
   */
  void setX(int value);

  /**
   * Returns the value of the '<em><b>Y</b></em>' attribute.
   * The default value is <code>"0"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Y</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Y</em>' attribute.
   * @see #setY(int)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Y()
   * @model default="0"
   * @generated
   */
  int getY();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getY <em>Y</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Y</em>' attribute.
   * @see #getY()
   * @generated
   */
  void setY(int value);

  /**
   * Returns the value of the '<em><b>Width</b></em>' attribute.
   * The default value is <code>"100"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Width</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Width</em>' attribute.
   * @see #setWidth(int)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Width()
   * @model default="100"
   * @generated
   */
  int getWidth();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getWidth <em>Width</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Width</em>' attribute.
   * @see #getWidth()
   * @generated
   */
  void setWidth(int value);

  /**
   * Returns the value of the '<em><b>Height</b></em>' attribute.
   * The default value is <code>"100"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Height</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Height</em>' attribute.
   * @see #setHeight(int)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Height()
   * @model default="100"
   * @generated
   */
  int getHeight();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getHeight <em>Height</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Height</em>' attribute.
   * @see #getHeight()
   * @generated
   */
  void setHeight(int value);

  /**
   * Returns the value of the '<em><b>Expanded Width</b></em>' attribute.
   * The default value is <code>"200"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expanded Width</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expanded Width</em>' attribute.
   * @see #setExpandedWidth(int)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_ExpandedWidth()
   * @model default="200"
   * @generated
   */
  int getExpandedWidth();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getExpandedWidth <em>Expanded Width</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expanded Width</em>' attribute.
   * @see #getExpandedWidth()
   * @generated
   */
  void setExpandedWidth(int value);

  /**
   * Returns the value of the '<em><b>Expanded Height</b></em>' attribute.
   * The default value is <code>"200"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expanded Height</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expanded Height</em>' attribute.
   * @see #setExpandedHeight(int)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_ExpandedHeight()
   * @model default="200"
   * @generated
   */
  int getExpandedHeight();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getExpandedHeight <em>Expanded Height</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expanded Height</em>' attribute.
   * @see #getExpandedHeight()
   * @generated
   */
  void setExpandedHeight(int value);

  /**
   * Returns the value of the '<em><b>Expanded</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expanded</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expanded</em>' attribute.
   * @see #setExpanded(boolean)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Expanded()
   * @model default="false"
   * @generated
   */
  boolean isExpanded();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#isExpanded <em>Expanded</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expanded</em>' attribute.
   * @see #isExpanded()
   * @generated
   */
  void setExpanded(boolean value);

  /**
   * Returns the value of the '<em><b>Subtype</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subtype</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subtype</em>' attribute.
   * @see #setSubtype(boolean)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Subtype()
   * @model default="false"
   * @generated
   */
  boolean isSubtype();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#isSubtype <em>Subtype</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subtype</em>' attribute.
   * @see #isSubtype()
   * @generated
   */
  void setSubtype(boolean value);

  /**
   * Returns the value of the '<em><b>Visible</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Visible</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Visible</em>' attribute.
   * @see #setVisible(boolean)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Visible()
   * @model default="true"
   * @generated
   */
  boolean isVisible();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#isVisible <em>Visible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visible</em>' attribute.
   * @see #isVisible()
   * @generated
   */
  void setVisible(boolean value);

  /**
   * Returns the value of the '<em><b>Model Link Target</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Link Target</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Link Target</em>' attribute.
   * @see #setModelLinkTarget(String)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_ModelLinkTarget()
   * @model default=""
   * @generated
   */
  String getModelLinkTarget();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getModelLinkTarget <em>Model Link Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Link Target</em>' attribute.
   * @see #getModelLinkTarget()
   * @generated
   */
  void setModelLinkTarget(String value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' attribute.
   * @see #setAnnotation(String)
   * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getModelObject_Annotation()
   * @model default=""
   * @generated
   */
  String getAnnotation();

  /**
   * Sets the value of the '{@link org.gems.test.deployment.emf.deployment.ModelObject#getAnnotation <em>Annotation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' attribute.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(String value);

} // ModelObject
