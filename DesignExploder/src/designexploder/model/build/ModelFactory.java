package designexploder.model.build;

import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.Node;

public interface ModelFactory<N extends Node<C>, C extends Connection> {

	NodeBuilder<N, C> createNode();
	
	ConnectionBuilder<C> createConnection();
	
	Diagram<N, C> createDiagram(Set<N> nodes);

	public interface NodeBuilder<N extends Node<C>, C extends Connection> {
	 
		void setBounds(Rectangle bounds);

		N getResult();
		
	}
	
	public interface ConnectionBuilder<C extends Connection> {
		
		void setSource(Node<C> source);
		
		void setTarget(Node<C> target);
		
		C getResult();
		
	}
	
}
