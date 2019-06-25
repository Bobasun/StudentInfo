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

public class Controller {

	private static Controller instance;
	private INodeService service = NodeService.getInstance();
	private List<ChangeListener> listListeners = new ArrayList<>();

	private Controller() {
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

//	public void save(GroupNode node, String parent) {
//		INode parentNode = service.find(parent);
//		if (!parentNode.getChildren().contains(node)) {
//			service.addNode(parentNode, node);
//		} else {
//			service.updateNode(null, node);
//
//		}
////		if (node.getRoot() == service.getRoot()) {	
////		}
//
//		notifyAllListeners(null);
//	}

	public void remove(INode node, String parentName) {
		GroupNode parent = (GroupNode) service.find(parentName);
		service.removeNode(parent, node);
		notifyAllListeners(null);
	}

	public void addListener(ChangeListener lis) {
		listListeners.add(lis);
	}

	private void notifyAllListeners(ChangeEvent event) {
		listListeners.forEach(listener -> listener.stateChanged(event));
//		for (int i = 0; i < list.size(); i++)
//			list.get(i).stateChanged(event);
	}

	public void removeListener(ChangeListener lis) {
		listListeners.remove(lis);
	}

	public INode getNode(String path, INode node) {
		GroupNode parent = (GroupNode) service.find(path);
		node.setParent(parent);
		return node;

	}

	public void save(INode selectedNode, INode newNode) {
		
		GroupNode parentNode = (GroupNode) service.find(selectedNode.getParent().getPath());
		if (parentNode.getChildren().contains((INode) selectedNode)) {
//			newNode.setParent(parentNode);
			service.updateNode(selectedNode, newNode);
			
		} else {
			service.addNode(parentNode, newNode);
		}
		
		notifyAllListeners(null);

	}

	public INode getNode(String path) {
		// TODO Auto-generated method stub
		return service.find(path);
	}

	public boolean isNodeExists(ItemNode selectedNode) {
		GroupNode parent = (GroupNode) getNode(selectedNode.getParent().getPath());
//		String checkPath = parent.getPath();
		service.getRoot().getChildren();
		
//		if()
		return false;
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