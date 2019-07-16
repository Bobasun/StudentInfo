package org.eclipsercp.studentinfo.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipsercp.studentinfo.controller.ChangeNodeEvent;
import org.eclipsercp.studentinfo.controller.ChangeNodeListener;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.undoredo.UndoRedoINodes;

public abstract class AbstractEditorPart extends EditorPart {

	protected boolean dirty;
	protected ChangeNodeListener listener;
	protected INode selectedNode;
	private UndoRedoINodes stack;
	private INode undoRedoNode;

	public INode getUndoRedoNode() {
		return undoRedoNode;
	}

	public void setUndoRedoNode(INode undoRedoNode) {
		this.undoRedoNode = undoRedoNode;
	}

	public INode getSelectedNode() {
		return selectedNode;
	}

	public UndoRedoINodes getStack() {
		return stack;
	}

	@Override
	public void dispose() {
		super.dispose();
		Controller.getInstance().removeListener(listener);
	}

	public void addSelectedNode(INode node) {
		selectedNode = node;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		doSave(createNewNode());
	}

	protected abstract INode createNewNode();

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		stack = new UndoRedoINodes();
	}

	@Override
	public boolean isDirty() {
		return this.dirty;
	}

	protected void setDirty(boolean dirty) {
		if (this.dirty != dirty) {
			this.dirty = dirty;
			this.firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {

	}

	@Override
	public void setFocus() {
	}

	class TextModifyListener implements ModifyListener {
		@Override
		public void modifyText(ModifyEvent e) {
			if (checkModifyFields()) {
				setDirty(true);
			} else {
				setDirty(false);
			}
		}
	}

	protected void doSave(INode node) {
		if (Controller.getInstance().isNodeExists(selectedNode.getParent())) {
			Controller.getInstance().save(selectedNode, node);
			if (Controller.getInstance().isNodeExists(node)) {
				stack.pushUndo(selectedNode);
				undoRedoNode = node;
				selectedNode = node;
				NodeEditorInput input = (NodeEditorInput) getEditorInput();
				input.setName(selectedNode.getPath() + getID());
				setDirty(false);
				setPartName(selectedNode.getName());
			}
		}
	}

	protected void createEditorContext(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		compositeSetting(composite);
		createItputItems(composite);
		addNodeListener(getActiveEditor());

	}

	protected void addNodeListener(EditorPart editor) {
		listener = new ChangeNodeListener() {
			public void stateChanged(ChangeNodeEvent event) {
				switch (event.getAction()) {
				case UPDATE_NODE:
					if (event.getNewNode() instanceof GroupNode) {
						if (selectedNode.getParent().getPath().equals(event.getNewNode().getPath())) {
							setFields(event.getNewNode());
						}
					}
					break;
				case REMOVE_NODE:
					if (selectedNode.getPath().contains(event.getOldNode().getPath())) {
						getSite().getPage().closeEditor(editor, false);
					}
					break;
				case ADD_NODE:
					break;
				default:
					break;
				}
			}
		};
		Controller.getInstance().addListener(listener);

	}

	protected abstract void setFields(INode node);

	protected abstract String getID();

	protected abstract EditorPart getActiveEditor();

	protected abstract void compositeSetting(Composite composite);

	protected abstract void createItputItems(Composite composite);

	protected abstract boolean checkModifyFields();

	public abstract void fillFields();

	public abstract void fillFields(INode node);

}
