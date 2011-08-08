package designexploder.model.extension.IoC;

import java.util.Set;

import designexploder.model.ModelExtension;
import designexploder.model.Node;
import designexploder.model.extension.common.Named;
import designexploder.model.extension.common.Naturalized;

public interface BeanNode extends Named, Naturalized, ModelExtension {

	Set<Dependency> getDependencies();
	
	void addDependency(Dependency dependency);

	void removeDependency(Dependency dependency);
	
	Set<IoCAwareMethod> getIoCAwareMethods();

	void addIoCAwareMethod(IoCAwareMethod method);

	void removeIoCAwareMethod(IoCAwareMethod method);

	Node getNode();

	void setNode(Node node);

}
