package org.eclipsercp.studentinfo.model;

import java.io.Serializable;

public interface INode extends Serializable{

	String getName();

	GroupNode getParent();

	boolean hasChildren();

	String toString();

	void setParent(GroupNode parent);

	GroupNode getRoot();

	String getPath();

	int hashCode();

	boolean equals(Object obj);

	void setName(String name);

}
