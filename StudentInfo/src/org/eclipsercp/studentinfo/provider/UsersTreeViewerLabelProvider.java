package org.eclipsercp.studentinfo.provider;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class UsersTreeViewerLabelProvider extends LabelProvider {

	private ResourceManager resourceManager;

	private ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return resourceManager;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof ItemNode) {
			return getResourceManager()
					.createImage(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.ITEM));
		} else if (element instanceof GroupNode) {
			return getResourceManager()
					.createImage(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.OPEN));
		}
		return super.getImage(element);
	}

}
