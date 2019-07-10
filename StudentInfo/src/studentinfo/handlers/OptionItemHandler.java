package studentinfo.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;

public class OptionItemHandler extends CommonHandler {

	public static final String OPTION1_COMMAND_ID = "StudentInfo.commands.optionItemCommand";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		State state1 = getStateItemOption();
		State state2 = getStateGroupOption();
		state1.setValue(true);
		state2.setValue(false);

		return null;
	}
}
