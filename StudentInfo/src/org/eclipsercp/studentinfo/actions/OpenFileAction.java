package org.eclipsercp.studentinfo.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;
import org.eclipsercp.studentinfo.view.UsersView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenFileAction extends Action implements IWorkbenchAction {
	
	public final static String ID = "org.eclipsercp.studentinfo.openfile";
	private IWorkbenchWindow window;

	public OpenFileAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Open");
		setToolTipText("Open");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.OPEN));

	}

	@Override
	public void run() {
		FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		dialog.setFilterPath("c:\\");
		String[] ext = new String[] { ".json" };
		dialog.setFilterExtensions(ext);
		String path = dialog.open();
		if (path != null) {
			readFile(path);
		}	
	}
	
	private void readFile(String path) {
		final ObjectMapper mapper = new ObjectMapper();
		try { 
			JsonNode node = mapper.readTree(new File(path));
			String name = node.get("name").asText();
			RootNode rootNode = new RootNode(name);
			JsonNode childrenNode = node.get("nodes");
			createChildren(childrenNode, rootNode);
			Controller.getInstance().setRootNode(rootNode);
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createChildren(JsonNode children, GroupNode parent) {
		Iterator<JsonNode> iterator = children.iterator();
		while (iterator.hasNext()) {
			JsonNode child = iterator.next();
			boolean isGroup = child.get("flag").asBoolean();
			if (!isGroup) {
				createItem(child, parent);
			} else {
				createGroup(child, parent);
			}
		}
	}

	public void createItem(JsonNode node, GroupNode parent) {
		String name = node.get("name").asText();
		String address = node.get("address").asText();
		String city = node.get("address").asText();
		int result = node.get("result").asInt();
		String imagePath = node.get("imagePath").asText();
		System.err.println(name + address + city);
		parent.addChildren(new ItemNode(name, address, city, result, imagePath));
	}

	public void createGroup(JsonNode node, GroupNode parent) {
		String name = node.get("name").asText();
		GroupNode groupNode = new GroupNode(name);
		JsonNode childrenNode = node.get("nodes");
		createChildren(childrenNode, groupNode);
		parent.addChildren(groupNode);
		
	}
	
	@Override
	public void dispose() {
	}

}
