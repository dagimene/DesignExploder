package designexploder.model.impl;

import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.Node;
import designexploder.model.build.ModelFactory;

public class ModelFactoryImpl<N extends Node<C>, C extends Connection> implements ModelFactory<N, C> {
	
	public NodeBuilder<N, C> createNode() {
		return new NodeBuilderImpl();
	}
	
	public ConnectionBuilder<C> createConnection() {
		return new ConnectionBuilderImpl();
	}
	
	protected class NodeBuilderImpl implements NodeBuilder<N, C>{
		
		protected N result;
		
		public void setBounds(Rectangle bounds) {
			getResult().setBounds(bounds);
		}
		
		public N getResult() {
			ensureInstantiation();
			return result;
		}
		
		protected void ensureInstantiation() {
			if(result == null) {
				result = instantiate();
			}
		}

		@SuppressWarnings("unchecked")
		protected N instantiate() {
			return (N) new NodeImpl<Connection>();
		}
	}
	
	protected class ConnectionBuilderImpl implements ConnectionBuilder<C> {
		private C result;

		@Override
		public void setSource(Node<C> source) {
			getResult().setSource(source);
		}

		@Override
		public void setTarget(Node<C> target) {
			getResult().setTarget(target);
		}

		public C getResult() {
			ensureInstantiation();
			return result;
		}
		
		protected void ensureInstantiation() {
			if(result == null) {
				result = instantiate(); 
			}
		}
		
		@SuppressWarnings("unchecked")
		protected C instantiate() {
			return (C) new ConnectionImpl();
		}
	}

	@Override
	public Diagram<N, C> createDiagram(Set<N> nodes) {
		DiagramImpl<N, C> result = new DiagramImpl<N, C>();
		for (N node : nodes) {
			result.addNode(node);
		}
		return result;
	}
}
