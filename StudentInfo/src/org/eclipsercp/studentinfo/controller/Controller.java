package org.eclipsercp.studentinfo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.INodeService;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;
import org.eclipsercp.studentinfo.view.ChangeNodeListener;

public class Controller {

	private static Controller instance;
	private INodeService service = NodeService.getInstance();
	private List<ChangeNodeListener> listListeners = new ArrayList<>();

	private Controller() {
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public void remove(INode node, String parentName) {
		GroupNode parent = (GroupNode) service.find(parentName);
		service.removeNode(parent, node);
		notifyAllListeners(createNodeEvent(EnumAction.REMOVE_NODE, node, null));
	}

	public void addListener(ChangeNodeListener lis) {
		listListeners.add(lis);
	}

	private void notifyAllListeners(ChangeNodeEvent event) {
		listListeners.forEach(listener -> listener.stateChanged(event));
	}

	public void removeListener(ChangeNodeListener lis) {
		listListeners.remove(lis);
	}

	public INode getNode(String path, INode node) {
		GroupNode parent = (GroupNode) service.find(path);
		node.setParent(parent);
		return node;

	}

	public void save(INode selectedNode, INode newNode) {
		EnumAction action;
		GroupNode parentNode = (GroupNode) service.find(selectedNode.getParent().getPath());
		if (parentNode.getChildren().contains((INode) selectedNode)) {
			service.updateNode(selectedNode, newNode);
			action = EnumAction.UPDATE_NODE;
		} else {
			service.addNode(parentNode, newNode);
			action = EnumAction.ADD_NODE;
		}
		if (isNodeExists(newNode)) {
			notifyAllListeners(createNodeEvent(action, selectedNode, newNode));
		}

	}
	
	private ChangeNodeEvent createNodeEvent(EnumAction action, INode oldNode, INode newNode) {
		return new ChangeNodeEvent(action, oldNode, newNode);
	}

	public INode getNode(String path) {
		// TODO Auto-generated method stub
		return service.find(path);
	}

	public boolean isNodeExists(INode node) {
		return service.find(node.getPath()) != null ? true : false;
	}
}

//public INode findParents(String name, List<INode> children) {
//
//	for(INode node : children) {
//		if (node.getName().equals(name)) {
//			System.err.println("yep");
//			return node;
//		} else {
//			System.err.println("nope");
//			return findParent(name, node.getChildren());
//		}
//	}
//