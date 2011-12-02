package designexploder.model.extension.IoC;

import designexploder.model.Connection;

import java.util.Set;

public interface TargetedIoCAwareMethod extends IoCAwareMethod {

    boolean isResolved();

    /**
     * A connections set with the bean instantiations associated to this TargetedIoCAwareMethod.
     * @return
     */
    Set<Connection> getIoCInstantiations();

    void addIoCInstantiation(Connection instantiation);

    void removeIoCInstantiation(Connection instantiation);

}
