package org.eclipsercp.studentinfo.model;

import java.util.List;

public interface INode {

	String getName();
	INode getParent();
	List<INode> getChildren();
	boolean hasChildren();

	String toString();
	void setParent(INode parent);
	INode getRoot();
	String getPath();
	
	
}
