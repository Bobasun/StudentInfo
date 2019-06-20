package org.eclipsercp.studentinfo;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipsercp.studentinfo.view.UsersView;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.setFixed(true);
		
		layout.addStandaloneView(UsersView.ID, false ,IPageLayout.LEFT, 0.5f, layout.getEditorArea());
	}
}
