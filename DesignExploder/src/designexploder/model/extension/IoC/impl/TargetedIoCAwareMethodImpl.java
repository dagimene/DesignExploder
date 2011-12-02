package designexploder.model.extension.IoC.impl;

import designexploder.model.Connection;
import designexploder.model.extension.IoC.TargetedIoCAwareMethod;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.common.Nature;

import java.util.HashSet;
import java.util.Set;

class TargetedIoCAwareMethodImpl extends IoCAwareMethodImpl implements TargetedIoCAwareMethod {

    private Set<Connection> iocInstantiations = new HashSet<Connection>();

    @Override
    public boolean isResolved() {
        Nature nature = super.getNature();
        return nature != IoCModelNatures.IOC_METHOD_FACTORY_UNRESOLVED &&
                nature != IoCModelNatures.IOC_METHOD_INSTANTIATE_UNRESOLVED;
    }

    @Override
    public Set<Connection> getIoCInstantiations() {
        return iocInstantiations;
    }

    @Override
    public void addIoCInstantiation(Connection instantiation) {
        iocInstantiations.add(instantiation);
        fireModelCollectionAlterEvent(IoCModelEventTypes.IOC_INSTANTIATION_ADDED, iocInstantiations, instantiation);
    }

    @Override
    public void removeIoCInstantiation(Connection instantiation) {
        iocInstantiations.remove(instantiation);
        fireModelCollectionAlterEvent(IoCModelEventTypes.IOC_INSTANTIATION_REMOVED, iocInstantiations, instantiation);
    }
}
