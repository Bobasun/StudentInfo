package org.eclipsercp.studentinfo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NodeService implements INodeService {

	private GroupNode root;
	private static NodeService nodeService;

	public static NodeService getInstance() {
		if (nodeService == null) {
			nodeService = new NodeService(new RootNode("folder"));
		}
		return nodeService;
	}

	private NodeService(GroupNode root) {
		this.root = root;

	}

	@Override
	public void removeNode(GroupNode parent, INode node) {
		if (parent.getChildren().contains(node)) {
			parent.getChildren().remove(node);
		}
	}

	private boolean hasChildrenListSameNameNode(List<INode> list, INode node) {
		Optional<INode> search = list.stream().filter(n -> n.getName().equals(node.getName()) 
				&& n.hasChildren() == node.hasChildren() ).findAny();
		return search.isPresent();
	}

	@Override
	public void addNode(GroupNode parent, INode node) {
		
		if (!hasChildrenListSameNameNode(parent.getChildren(), node) ) {
			parent.getChildren().add(node);
			node.setParent(parent);
		}
	}

	@Override
	public void updateNode(INode oldNode, INode newNode) {
		if (!hasChildrenListSameNameNode(oldNode.getParent().getChildren(), newNode) || oldNode.getName().equals(newNode.getName())) {
		newNode.setParent(oldNode.getParent());
		if (oldNode.hasChildren()) {
			((GroupNode) newNode).addChildren(((GroupNode) oldNode).getChildren());
			for (int i = 0; i < ((GroupNode) newNode).getChildren().size(); i++) {
				((GroupNode) newNode).getChildren().get(i).setParent((GroupNode) newNode);
			}
		}
		int index = oldNode.getParent().getChildren().indexOf(oldNode);
		oldNode.getParent().getChildren().set(index, newNode);
		} 
	}

	@Override
	public GroupNode getRoot() {
		return root;
	}

	public INode[] getAllNodes() {
		INode[] nodes = new INode[1];
		nodes[0] = root;
		return nodes;
	}

	@Override
	public INode find(String path) {
			return find (path, INode.class);
	}
	
	@Override
	public INode find(String path, Class<? extends INode> class_) {

		String[] groups = path.substring(1, path.length()).split("/");
		if (root.getName().equals(groups[0])) {
			return find(groups, root.getChildren(), class_);
		} else {
			return null;
		}
	}

	private INode find(String[] groups, List<INode> children, Class<? extends INode> class_) {
       INode localNode = root;
       for (int i = 1; i < groups.length; i++) {
			List<INode> localNodes = find(groups[i], children, class_);
			for(INode node: localNodes) {
				if(i==groups.length-1 && class_.isAssignableFrom(node.getClass())) {
					return node;
				}
				if (node.hasChildren()) {
					children = ((GroupNode) node).getChildren();
					
				}
			}
			
//			if (localNode != null) {
//				if (localNode.hasChildren()) {
//					children = ((GroupNode) localNode).getChildren();
//				}
//			} else {
//				return null;
//			}
		}
		return localNode;
	}

	private List<INode> find(String name, List<INode> nodes, Class<? extends INode> class_) {
		
		List<INode> list = new ArrayList<>();
		for (INode node : nodes) {
			if (node.getName().equals(name)) {
				list.add(node);
				
			}
		}
		return list;

	}

//	@Override
//	public void updateNode(INode selectedNode, String name) {
//		selectedNode.setName(name);
//	}

}
