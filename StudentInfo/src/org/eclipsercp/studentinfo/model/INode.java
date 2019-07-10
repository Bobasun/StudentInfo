package org.eclipsercp.studentinfo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "flag")
public interface INode extends Serializable{

	String getName();

	GroupNode getParent();

	
	@JsonProperty("flag")
	boolean hasChildren();
	@JsonIgnore
	String toString();

	void setParent(GroupNode parent);
	@JsonIgnore
	GroupNode getRoot();
	@JsonIgnore
	String getPath();
	@JsonIgnore
	int hashCode();
	@JsonProperty("parentPath")
	public String getParentPath(); 
	
	boolean equals(Object obj);

	void setName(String name);

}
