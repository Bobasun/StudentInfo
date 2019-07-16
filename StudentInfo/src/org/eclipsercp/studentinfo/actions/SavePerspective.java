package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipsercp.studentinfo.ApplicationWorkbenchAdvisor;

public class SavePerspective extends Action implements IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.saveperspective";

	public SavePerspective(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Save perspective");
		setToolTipText("SavePerspective");
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		IPerspectiveRegistry perspectiveRegistry = window.getWorkbench().getPerspectiveRegistry();
		IPerspectiveDescriptor personalPerspectiveDescriptor = perspectiveRegistry
				.findPerspectiveWithId(ApplicationWorkbenchAdvisor.PERSPECTIVE_ID);
		// hardcoded!!!

		if (page != null && personalPerspectiveDescriptor != null) {
			// ... other stuff like different confirm dialogs
			page.savePerspectiveAs(personalPerspectiveDescriptor);
		}
	}

	@Override
	public void dispose() {

	}

}
