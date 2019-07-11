package studentinfo.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;

public class OptionGroupHandler extends AbstractCommonHandler {

	public static final String OPTION2_COMMAND_ID = "StudentInfo.commands.optionGroupCommand";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		State state1 = getStateItemOption();
		State state2 = getStateGroupOption();
		state1.setValue(false);
		state2.setValue(true);
		return null;
	}

}
