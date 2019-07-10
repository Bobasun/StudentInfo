package studentinfo.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

import studentinfo.states.StateOption1;
import studentinfo.states.StateOption2;

public abstract class CommonHandler extends AbstractHandler {

	private ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

	protected State getStateItemOption() {
		Command command =service.getCommand(OptionItemHandler.OPTION1_COMMAND_ID);
		return command.getState(StateOption1.STATE_ID);
		
	}
	
	protected State getStateGroupOption() {
		Command command =service.getCommand(OptionGroupHandler.OPTION2_COMMAND_ID);
		return command.getState(StateOption2.STATE_ID);
	}
}
