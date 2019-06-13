package org.eclipsercp.studentinfo;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(UsersView.ID, true ,IPageLayout.LEFT, 0.5f, layout.getEditorArea());
	}
}
