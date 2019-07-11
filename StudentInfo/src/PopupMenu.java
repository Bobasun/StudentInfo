import java.net.URL;
import java.nio.file.Paths;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

import studentinfo.handlers.NewNodeHandler;
import studentinfo.handlers.OptionGroupHandler;
import studentinfo.handlers.OptionItemHandler;
import studentinfo.handlers.RemoveNodeHandler;
import studentinfo.states.StateOption1;
import studentinfo.states.StateOption2;

public class PopupMenu extends ExtensionContributionFactory {

	private static final String ADD = "Add";
	private static final String REMOVE = "Remove";
	private static final String OPTIONS1 = "Option1";
	private static final String OPTIONS2 = "Option2";

	public PopupMenu() {
	}

	@Override
	public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

		createItem(serviceLocator, additions, ADD, NewNodeHandler.ADD_COMMAND_ID);
		createItem(serviceLocator, additions, REMOVE, RemoveNodeHandler.REMOVE_COMMAND_ID);
		additions.addContributionItem(new Separator(), null);
		createItem(serviceLocator, additions, OPTIONS1, OptionItemHandler.OPTION1_COMMAND_ID);
		createItem(serviceLocator, additions, OPTIONS2, OptionGroupHandler.OPTION2_COMMAND_ID);
	}

	private void createItem(IServiceLocator serviceLocator, IContributionRoot additions, String id, String commandId) {
		CommandContributionItemParameter param = new CommandContributionItemParameter(serviceLocator, id, commandId,
				SWT.PUSH);
		param.label = id;
		
		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command;
		State state = null;
		if (id.equals(OPTIONS1)) {
			command = service.getCommand(OptionItemHandler.OPTION1_COMMAND_ID);
			state = command.getState(StateOption1.STATE_ID);
		} else if (id.equals(OPTIONS2)) {
			command = service.getCommand(OptionGroupHandler.OPTION2_COMMAND_ID);
			state = command.getState(StateOption2.STATE_ID);
		} 
		if (state != null && (Boolean) state.getValue()) {
			param.icon = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.OPTION);
		}

		CommandContributionItem addItem = new CommandContributionItem(param);
		addItem.setVisible(true);
		additions.addContributionItem(addItem, null);

	}

}
