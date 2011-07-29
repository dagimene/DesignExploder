package designexploder.persistence.xml;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBasicDataProvider;
import designexploder.util.DexModelUtil;
import designexploder.util.TypeAdapterIterator;

public class XMLBasicModelDataProvider implements ModelBasicDataProvider {

	private final IFile file;

	public XMLBasicModelDataProvider(IFile file) {
		this.file = file;
	}

	@Override
	public Iterator<NodeBasicDataProvider> getNodes() {
		XMLMemento document;
		try {
			document = XMLMemento.createReadRoot(new InputStreamReader(file.getContents()));
			return getNodesSafe(document);
		} catch (WorkbenchException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return Collections.<NodeBasicDataProvider>emptySet().iterator();
	}

	@Override
	public Iterator<ConnectionBasicDataProvider> getConnections() {
		XMLMemento document;
		try {
			document = XMLMemento.createReadRoot(new InputStreamReader(file.getContents()));
			return getConnectionsSafe(document);
		} catch (WorkbenchException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return Collections.<ConnectionBasicDataProvider>emptySet().iterator();
	}

	protected void setNodeData(IMemento memento, Node node, NodeContainer root) {
		if(node.isResizeable()) {
			ModelProperties.BOUNDS.read(memento, node);
		} else { 
			ModelProperties.LOCATION.read(memento, node);
		}
		String containerId = memento.getString("containerId");
		if(containerId != null) {
			NodeContainer container = asContainer(root.findNode(containerId));
			if(container != null) {
				DexModelUtil.reparentNode(node, container);
			}
		}
	}

	protected void setConnectionData(IMemento memento, Node source, Node target, NodeContainer root) {
		// No basic data being saved
	}
	
	private Iterator<NodeBasicDataProvider> getNodesSafe(XMLMemento document) {
		return new TypeAdapterIterator<IMemento, NodeBasicDataProvider>(Arrays.asList(document.getChildren("node")).iterator()) {
			@Override
			protected NodeBasicDataProvider adapt(final IMemento memento) {
				return new NodeBasicDataProvider() {

					@Override
					public String getId() {
						return memento.getString("id");
					}

					@Override
					public void setBasicData(Node node, NodeContainer root) {
						setNodeData(memento, node, root);
					}
				};
			}
		};
	}
	
	private Iterator<ConnectionBasicDataProvider> getConnectionsSafe(XMLMemento document) {
		return new TypeAdapterIterator<IMemento, ConnectionBasicDataProvider>(Arrays.asList(document.getChildren("connection")).iterator()) {
			@Override
			protected ConnectionBasicDataProvider adapt(final IMemento memento) {
				return new ConnectionBasicDataProvider() {

					@Override
					public String getId() {
						return memento.getString("id");
					}

					@Override
					public String getSourceID() {
						return memento.getString("source");
					}

					@Override
					public String getTargetID() {
						return memento.getString("target");
					}

					@Override
					public void setBasicData(Connection connection, Node source, Node target, NodeContainer root) {
						setConnectionData(memento, source, target, root);
					}

				};
			}
		};
	}

	protected NodeContainer asContainer(Node node) {
		return node instanceof NodeContainer ? (NodeContainer) node : null;
	}
}
