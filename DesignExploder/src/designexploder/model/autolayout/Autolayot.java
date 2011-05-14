package designexploder.model.autolayout;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.Node;

public interface Autolayot {

	<N extends Node<C>, C extends Connection> void autolayout(Diagram<N, C> diagram);
	
}
