package org.eclipsercp.studentinfo.model;

import java.util.List;

public interface Node {

	String getName();
	Node getParent();
	List<Node> getChildren();
	boolean hasChildren();
	
	void addNode(Node node);
	void removeNode(Node node);
	String toString();
	
	
}
