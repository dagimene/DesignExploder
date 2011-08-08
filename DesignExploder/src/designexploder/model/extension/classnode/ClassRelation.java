package designexploder.model.extension.classnode;

import designexploder.model.ModelExtension;
import designexploder.model.extension.common.Naturalized;

/**
 *
 *	Connection nature that holds class relation data.
 * <p>
 * 	<b>Fires:</b>
 * 	<ul>
 * 		<li>ClassModelEventTypes.ORIGIN_CHANGED</li>
 * 		<li>CommonModelEventTypes.NAME_CHANGED</li>
 * 		<li>CommonModelEventTypes.NATURE_CHANGED</li>
 * 	</ul>
 * </p>
 */
public interface ClassRelation extends InmutableNamed, Naturalized, ModelExtension {
	
	int getSourceCardinality();
	
	int getTargetCardinality();

	void setSourceCardinality(int cardinality);
	
	void setTargetCardinality(int cardinality);
	
	/**
	 * @return The attribute that originated a composition or association natured connection, or null.
	 */
	Attribute getOrigin();
	
	void setOrigin(Attribute origin);
	
}
