package org.eclipsercp.studentinfo;


import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.Node;
import org.eclipsercp.studentinfo.model.ItemNode;

public class UsersView extends ViewPart {

	public static final String ID = "org.eclipsercp.studentinfo.treeviewer";
	
	private TreeViewer treeViewer;
	
	public UsersView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent);
		treeViewer.setContentProvider(new UsersTreeViewerContentProvider());
		treeViewer.setInput(getRootNode());
	}

	private Node[] getRootNode() {
		Node node = new RootNode("folder");
		Node group1 = new GroupNode(node, "Group1");
		Node user1 = new ItemNode("Vova", "sd", "ds", 1, group1);
		Node user2 = new ItemNode("Sasha", "sda", "dsds", 2, group1);
		Node user3 = new ItemNode("Maksim", "sd", "ds", 1, group1);
		Node user4 = new ItemNode("Vasya", "sda", "dsds", 2, group1);
		group1.addNode(user1);
		group1.addNode(user2);
		group1.addNode(user3);
		group1.addNode(user4);
		node.addNode(group1);
		
		GroupNode group2 = new GroupNode(node, "Group2");
		Node user5 = new ItemNode("Kolya", "sd", "ds", 1, group2);
		Node user6 = new ItemNode("Ann", "sda", "dsds", 2, group2);
		Node user7 = new ItemNode("Sveta", "sd", "ds", 1, group2);
		Node user8 = new ItemNode("Masha", "sda", "dsds", 2, group2);
		group2.addNode(user5);
		group2.addNode(user6);
		group2.addNode(user7);
		group2.addNode(user8);
		node.addNode(group2);
		
		Node[] nodes = new Node[1];
		nodes[0] = node;
		return nodes;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	

}
