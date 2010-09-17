/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.gems.test.deployment.emf.deployment.DeploymentPackage;
import org.gems.test.deployment.emf.deployment.ModelObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getX <em>X</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getY <em>Y</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getExpandedWidth <em>Expanded Width</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getExpandedHeight <em>Expanded Height</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#isExpanded <em>Expanded</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#isSubtype <em>Subtype</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#isVisible <em>Visible</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getModelLinkTarget <em>Model Link Target</em>}</li>
 *   <li>{@link org.gems.test.deployment.emf.deployment.impl.ModelObjectImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelObjectImpl extends EObjectImpl implements ModelObject
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = "0";

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = "0";

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getX() <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getX()
   * @generated
   * @ordered
   */
  protected static final int X_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getX() <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getX()
   * @generated
   * @ordered
   */
  protected int x = X_EDEFAULT;

  /**
   * The default value of the '{@link #getY() <em>Y</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getY()
   * @generated
   * @ordered
   */
  protected static final int Y_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getY()
   * @generated
   * @ordered
   */
  protected int y = Y_EDEFAULT;

  /**
   * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWidth()
   * @generated
   * @ordered
   */
  protected static final int WIDTH_EDEFAULT = 100;

  /**
   * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWidth()
   * @generated
   * @ordered
   */
  protected int width = WIDTH_EDEFAULT;

  /**
   * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHeight()
   * @generated
   * @ordered
   */
  protected static final int HEIGHT_EDEFAULT = 100;

  /**
   * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHeight()
   * @generated
   * @ordered
   */
  protected int height = HEIGHT_EDEFAULT;

  /**
   * The default value of the '{@link #getExpandedWidth() <em>Expanded Width</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpandedWidth()
   * @generated
   * @ordered
   */
  protected static final int EXPANDED_WIDTH_EDEFAULT = 200;

  /**
   * The cached value of the '{@link #getExpandedWidth() <em>Expanded Width</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpandedWidth()
   * @generated
   * @ordered
   */
  protected int expandedWidth = EXPANDED_WIDTH_EDEFAULT;

  /**
   * The default value of the '{@link #getExpandedHeight() <em>Expanded Height</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpandedHeight()
   * @generated
   * @ordered
   */
  protected static final int EXPANDED_HEIGHT_EDEFAULT = 200;

  /**
   * The cached value of the '{@link #getExpandedHeight() <em>Expanded Height</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpandedHeight()
   * @generated
   * @ordered
   */
  protected int expandedHeight = EXPANDED_HEIGHT_EDEFAULT;

  /**
   * The default value of the '{@link #isExpanded() <em>Expanded</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExpanded()
   * @generated
   * @ordered
   */
  protected static final boolean EXPANDED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isExpanded() <em>Expanded</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExpanded()
   * @generated
   * @ordered
   */
  protected boolean expanded = EXPANDED_EDEFAULT;

  /**
   * The default value of the '{@link #isSubtype() <em>Subtype</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSubtype()
   * @generated
   * @ordered
   */
  protected static final boolean SUBTYPE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSubtype() <em>Subtype</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSubtype()
   * @generated
   * @ordered
   */
  protected boolean subtype = SUBTYPE_EDEFAULT;

  /**
   * The default value of the '{@link #isVisible() <em>Visible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVisible()
   * @generated
   * @ordered
   */
  protected static final boolean VISIBLE_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isVisible() <em>Visible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVisible()
   * @generated
   * @ordered
   */
  protected boolean visible = VISIBLE_EDEFAULT;

  /**
   * The default value of the '{@link #getModelLinkTarget() <em>Model Link Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelLinkTarget()
   * @generated
   * @ordered
   */
  protected static final String MODEL_LINK_TARGET_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getModelLinkTarget() <em>Model Link Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelLinkTarget()
   * @generated
   * @ordered
   */
  protected String modelLinkTarget = MODEL_LINK_TARGET_EDEFAULT;

  /**
   * The default value of the '{@link #getAnnotation() <em>Annotation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected static final String ANNOTATION_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected String annotation = ANNOTATION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelObjectImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return DeploymentPackage.Literals.MODEL_OBJECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(String newId)
  {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getX()
  {
    return x;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setX(int newX)
  {
    int oldX = x;
    x = newX;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__X, oldX, x));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getY()
  {
    return y;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setY(int newY)
  {
    int oldY = y;
    y = newY;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__Y, oldY, y));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getWidth()
  {
    return width;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWidth(int newWidth)
  {
    int oldWidth = width;
    width = newWidth;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__WIDTH, oldWidth, width));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getHeight()
  {
    return height;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHeight(int newHeight)
  {
    int oldHeight = height;
    height = newHeight;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__HEIGHT, oldHeight, height));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getExpandedWidth()
  {
    return expandedWidth;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpandedWidth(int newExpandedWidth)
  {
    int oldExpandedWidth = expandedWidth;
    expandedWidth = newExpandedWidth;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__EXPANDED_WIDTH, oldExpandedWidth, expandedWidth));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getExpandedHeight()
  {
    return expandedHeight;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpandedHeight(int newExpandedHeight)
  {
    int oldExpandedHeight = expandedHeight;
    expandedHeight = newExpandedHeight;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__EXPANDED_HEIGHT, oldExpandedHeight, expandedHeight));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isExpanded()
  {
    return expanded;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpanded(boolean newExpanded)
  {
    boolean oldExpanded = expanded;
    expanded = newExpanded;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__EXPANDED, oldExpanded, expanded));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSubtype()
  {
    return subtype;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubtype(boolean newSubtype)
  {
    boolean oldSubtype = subtype;
    subtype = newSubtype;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__SUBTYPE, oldSubtype, subtype));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isVisible()
  {
    return visible;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVisible(boolean newVisible)
  {
    boolean oldVisible = visible;
    visible = newVisible;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__VISIBLE, oldVisible, visible));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getModelLinkTarget()
  {
    return modelLinkTarget;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelLinkTarget(String newModelLinkTarget)
  {
    String oldModelLinkTarget = modelLinkTarget;
    modelLinkTarget = newModelLinkTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__MODEL_LINK_TARGET, oldModelLinkTarget, modelLinkTarget));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAnnotation()
  {
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation(String newAnnotation)
  {
    String oldAnnotation = annotation;
    annotation = newAnnotation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.MODEL_OBJECT__ANNOTATION, oldAnnotation, annotation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case DeploymentPackage.MODEL_OBJECT__NAME:
        return getName();
      case DeploymentPackage.MODEL_OBJECT__ID:
        return getId();
      case DeploymentPackage.MODEL_OBJECT__X:
        return new Integer(getX());
      case DeploymentPackage.MODEL_OBJECT__Y:
        return new Integer(getY());
      case DeploymentPackage.MODEL_OBJECT__WIDTH:
        return new Integer(getWidth());
      case DeploymentPackage.MODEL_OBJECT__HEIGHT:
        return new Integer(getHeight());
      case DeploymentPackage.MODEL_OBJECT__EXPANDED_WIDTH:
        return new Integer(getExpandedWidth());
      case DeploymentPackage.MODEL_OBJECT__EXPANDED_HEIGHT:
        return new Integer(getExpandedHeight());
      case DeploymentPackage.MODEL_OBJECT__EXPANDED:
        return isExpanded() ? Boolean.TRUE : Boolean.FALSE;
      case DeploymentPackage.MODEL_OBJECT__SUBTYPE:
        return isSubtype() ? Boolean.TRUE : Boolean.FALSE;
      case DeploymentPackage.MODEL_OBJECT__VISIBLE:
        return isVisible() ? Boolean.TRUE : Boolean.FALSE;
      case DeploymentPackage.MODEL_OBJECT__MODEL_LINK_TARGET:
        return getModelLinkTarget();
      case DeploymentPackage.MODEL_OBJECT__ANNOTATION:
        return getAnnotation();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DeploymentPackage.MODEL_OBJECT__NAME:
        setName((String)newValue);
        return;
      case DeploymentPackage.MODEL_OBJECT__ID:
        setId((String)newValue);
        return;
      case DeploymentPackage.MODEL_OBJECT__X:
        setX(((Integer)newValue).intValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__Y:
        setY(((Integer)newValue).intValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__WIDTH:
        setWidth(((Integer)newValue).intValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__HEIGHT:
        setHeight(((Integer)newValue).intValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED_WIDTH:
        setExpandedWidth(((Integer)newValue).intValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED_HEIGHT:
        setExpandedHeight(((Integer)newValue).intValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED:
        setExpanded(((Boolean)newValue).booleanValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__SUBTYPE:
        setSubtype(((Boolean)newValue).booleanValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__VISIBLE:
        setVisible(((Boolean)newValue).booleanValue());
        return;
      case DeploymentPackage.MODEL_OBJECT__MODEL_LINK_TARGET:
        setModelLinkTarget((String)newValue);
        return;
      case DeploymentPackage.MODEL_OBJECT__ANNOTATION:
        setAnnotation((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case DeploymentPackage.MODEL_OBJECT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__ID:
        setId(ID_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__X:
        setX(X_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__Y:
        setY(Y_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__WIDTH:
        setWidth(WIDTH_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__HEIGHT:
        setHeight(HEIGHT_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED_WIDTH:
        setExpandedWidth(EXPANDED_WIDTH_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED_HEIGHT:
        setExpandedHeight(EXPANDED_HEIGHT_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED:
        setExpanded(EXPANDED_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__SUBTYPE:
        setSubtype(SUBTYPE_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__VISIBLE:
        setVisible(VISIBLE_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__MODEL_LINK_TARGET:
        setModelLinkTarget(MODEL_LINK_TARGET_EDEFAULT);
        return;
      case DeploymentPackage.MODEL_OBJECT__ANNOTATION:
        setAnnotation(ANNOTATION_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case DeploymentPackage.MODEL_OBJECT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case DeploymentPackage.MODEL_OBJECT__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case DeploymentPackage.MODEL_OBJECT__X:
        return x != X_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__Y:
        return y != Y_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__WIDTH:
        return width != WIDTH_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__HEIGHT:
        return height != HEIGHT_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED_WIDTH:
        return expandedWidth != EXPANDED_WIDTH_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED_HEIGHT:
        return expandedHeight != EXPANDED_HEIGHT_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__EXPANDED:
        return expanded != EXPANDED_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__SUBTYPE:
        return subtype != SUBTYPE_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__VISIBLE:
        return visible != VISIBLE_EDEFAULT;
      case DeploymentPackage.MODEL_OBJECT__MODEL_LINK_TARGET:
        return MODEL_LINK_TARGET_EDEFAULT == null ? modelLinkTarget != null : !MODEL_LINK_TARGET_EDEFAULT.equals(modelLinkTarget);
      case DeploymentPackage.MODEL_OBJECT__ANNOTATION:
        return ANNOTATION_EDEFAULT == null ? annotation != null : !ANNOTATION_EDEFAULT.equals(annotation);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (Name: ");
    result.append(name);
    result.append(", Id: ");
    result.append(id);
    result.append(", X: ");
    result.append(x);
    result.append(", Y: ");
    result.append(y);
    result.append(", Width: ");
    result.append(width);
    result.append(", Height: ");
    result.append(height);
    result.append(", ExpandedWidth: ");
    result.append(expandedWidth);
    result.append(", ExpandedHeight: ");
    result.append(expandedHeight);
    result.append(", Expanded: ");
    result.append(expanded);
    result.append(", Subtype: ");
    result.append(subtype);
    result.append(", Visible: ");
    result.append(visible);
    result.append(", ModelLinkTarget: ");
    result.append(modelLinkTarget);
    result.append(", Annotation: ");
    result.append(annotation);
    result.append(')');
    return result.toString();
  }

} //ModelObjectImpl
