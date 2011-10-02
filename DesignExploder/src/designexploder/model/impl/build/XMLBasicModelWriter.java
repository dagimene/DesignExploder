package designexploder.model.impl.build;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.XMLMemento;

import designexploder.model.ContainerNode;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;

public class XMLBasicModelWriter implements ModelBuilder {
	
	private IFile source;

	public XMLBasicModelWriter(IFile source) {
		this.source = source;
	}

	public NodeContainer build(NodeContainer diagram) {
		XMLMemento xml = XMLMemento.createWriteRoot("diagram");
		writeNodes(diagram, diagram.getNodes(), xml);
		doWrite(null , xml);
		return diagram;
	}

	protected void doWrite(IProgressMonitor monitor, final XMLMemento xml) {
		PipedInputStream inputStream = new PipedInputStream();
		try {
			final PipedOutputStream outputStream = new PipedOutputStream(inputStream);
			new Thread() {
				@Override
				public void run() {
					try {
						xml.save(new OutputStreamWriter(outputStream));
						outputStream.flush();
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			source.setContents(inputStream, false, true, monitor);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}		

	protected void writeNodes(NodeContainer diagram, List<Node> nodes, IMemento memento) {
		for (Node node : nodes) {
			writeNode(memento, node, diagram);
		}
	}

	protected void writeNode(IMemento memento, Node node, NodeContainer root) {
		IMemento xmlNode = memento.createChild("node");
		writeNodeData(node, xmlNode, root);
		if(node instanceof ContainerNode) {
			writeNodes(root, ((NodeContainer) node).getNodes(), memento);
		}
	}

	protected void writeNodeData(Node node, IMemento memento, NodeContainer root) {
		ModelProperties.ID.write(memento, node);
		if(node.isResizeable()) {
			ModelProperties.BOUNDS.write(memento, node);
		} else { 
			ModelProperties.LOCATION.write(memento, node);
		}
		NodeContainer container = node.getNodeContainer();
		if(container != root) {
			// diagram is the only not-node node-container
			memento.putString("containerId", ((ContainerNode)container).getId());
		}
	}
}
