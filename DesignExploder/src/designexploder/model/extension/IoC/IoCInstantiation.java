package designexploder.model.extension.IoC;

import designexploder.model.ModelExtension;
import designexploder.model.extension.classnode.InmutableNamed;
import designexploder.model.extension.common.InmutableNaturalized;

/**
 * Connection nature that holds bean injection data.
 *	<p>
 * 		<b>Fires:</b>
 * 		<ul>
 * 			<li>ClassModelEventTypes.IOC_AWARE_FACTORY_METHOD_CHANGED</li>
 * 			<li>CommonModelEventTypes.NAME_CHANGED</li>
 * 			<li>CommonModelEventTypes.NATURE_CHANGED</li>
 * 		</ul>
 * 	</p>
 */
public interface IoCInstantiation extends InmutableNamed, InmutableNaturalized, ModelExtension {
	
    TargetedIoCAwareMethod getTargetedIoCAwareMethod();

    void setTargetedIoCAwareMethod(TargetedIoCAwareMethod targetedIoCAwareMethod);

}
