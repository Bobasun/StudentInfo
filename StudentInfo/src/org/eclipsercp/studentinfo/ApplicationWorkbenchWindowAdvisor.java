package org.eclipsercp.studentinfo;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.dnd.NodeTransfer;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.RootNode;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(800, 500));
		configurer.setShowCoolBar(true);
		configurer.setShowMenuBar(true);
		configurer.setShowStatusLine(false);
		configurer.setShowPerspectiveBar(true);
		configurer.setTitle("Student Info"); //$NON-NLS-1$
		configurer.addEditorAreaTransfer(NodeTransfer.getInstance());

		configurer.configureEditorAreaDropListener(new DropTargetListener() {

			@Override
			public void dropAccept(DropTargetEvent event) {

			}

			@Override
			public void drop(DropTargetEvent event) {
				if (NodeTransfer.getInstance().isSupportedType(event.dataTypes[0])) {
					INode node = (INode) event.data;
					if (node instanceof RootNode) {
						return;
					}
					if (node instanceof ItemNode) {
						node = Controller.getInstance().findNode(node.getPath(), ItemNode.class);
					} else {
						node = Controller.getInstance().findNode(node.getPath(), GroupNode.class);
					}
					String editorId = getEditorId(node);
					try {
						AbstractEditorPart editor = (AbstractEditorPart) configurer.getWindow().getActivePage()
								.openEditor(new NodeEditorInput(node.getPath() + editorId), editorId);
						editor.addSelectedNode(node);
						editor.fillFields();
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			}

			private String getEditorId(INode node) {
				if (node instanceof ItemNode) {
					return ItemEditor.ID;
				} else if (node instanceof GroupNode) {
					return GroupEditor.ID;
				}
				return null;
			}

			@Override
			public void dragOver(DropTargetEvent event) {
			}

			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				if ((event.operations == DND.DROP_DEFAULT)) {
					event.detail = DND.DROP_NONE;
				}
			}

			@Override
			public void dragLeave(DropTargetEvent event) {
				// TODO Auto-generated method stub
			}

			@Override
			public void dragEnter(DropTargetEvent event) {
				if (!(event.detail == DND.DROP_NONE)) {
					event.detail = DND.DROP_COPY;
				}

			}
		});

	}
}
