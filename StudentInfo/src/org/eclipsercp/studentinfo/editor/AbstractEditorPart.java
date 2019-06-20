package org.eclipsercp.studentinfo.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class AbstractEditorPart extends EditorPart {
	
	protected boolean dirty;
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return this.dirty;
	}

	protected void setDirty(boolean dirty) {
	       if (this.dirty != dirty) {
	           this.dirty = dirty;
	            
	           // Notify PROP_DIRTY changes to Workbench.
	           this.firePropertyChange(IEditorPart.PROP_DIRTY);
	       }
	   }
	
	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}