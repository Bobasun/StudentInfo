package org.eclipsercp.studentinfo.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;
import org.eclipsercp.studentinfo.view.UsersView;

import com.fasterxml.jackson.databind.ObjectMapper;



public class SaveFileAction extends Action implements IWorkbenchAction {

	public final static String ID = "org.eclipsercp.studentinfo.savefile";
	private IWorkbenchWindow window;

	public SaveFileAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Save");
		setToolTipText("Save");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.SAVE));
	}

	@Override
	public void run() {
		UsersView view = (UsersView) window.getActivePage().findView(UsersView.ID);
		System.err.println(view.getRootNode()[0].getName());
		saveToJson(view.getRootNode()[0]);
		
	}

	private void saveToJson(INode node) {
		FileDialog dialog = new FileDialog(window.getShell(), SWT.SAVE);
		dialog.setFilterPath("c:\\");
		String[] ext = new String[] { ".json" };
		dialog.setFilterExtensions(ext);
		dialog.setFileName("*" + ".json");
		String path = dialog.open();
		if (path != null) {
			writeFile(path, node);
		}
	}

	private void writeFile(String path, INode node) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(path), node);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
