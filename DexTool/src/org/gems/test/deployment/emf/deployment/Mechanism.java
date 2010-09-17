/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.gems.test.deployment.emf.deployment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Mechanism</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.gems.test.deployment.emf.deployment.DeploymentPackage#getMechanism()
 * @model instanceClass="org.gems.test.deployment.emf.deployment.Mechanism"
 * @generated
 */
public final class Mechanism extends AbstractEnumerator
{
  /**
   * The '<em><b>Manual</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Manual</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MANUAL_LITERAL
   * @model name="manual"
   * @generated
   * @ordered
   */
  public static final int MANUAL = 0;

  /**
   * The '<em><b>Automated</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Automated</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AUTOMATED_LITERAL
   * @model name="automated"
   * @generated
   * @ordered
   */
  public static final int AUTOMATED = 1;

  /**
   * The '<em><b>Manual</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MANUAL
   * @generated
   * @ordered
   */
  public static final Mechanism MANUAL_LITERAL = new Mechanism(MANUAL, "manual", "manual");

  /**
   * The '<em><b>Automated</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AUTOMATED
   * @generated
   * @ordered
   */
  public static final Mechanism AUTOMATED_LITERAL = new Mechanism(AUTOMATED, "automated", "automated");

  /**
   * An array of all the '<em><b>Mechanism</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final Mechanism[] VALUES_ARRAY =
    new Mechanism[]
    {
      MANUAL_LITERAL,
      AUTOMATED_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Mechanism</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Mechanism</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static Mechanism get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      Mechanism result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Mechanism</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static Mechanism getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      Mechanism result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Mechanism</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static Mechanism get(int value)
  {
    switch (value)
    {
      case MANUAL: return MANUAL_LITERAL;
      case AUTOMATED: return AUTOMATED_LITERAL;
    }
    return null;
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private Mechanism(int value, String name, String literal)
  {
    super(value, name, literal);
  }

} //Mechanism
