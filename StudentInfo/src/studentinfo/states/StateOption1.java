package studentinfo.states;

import org.eclipse.core.commands.State;

public class StateOption1 extends State {

	public final static String STATE_ID = "StudentInfo.commands.optionItem.state";

	private Boolean val;

	public StateOption1() {
		val = true;
	}

	@Override
	public Object getValue() {
		return val;
	}

	@Override
	public void setValue(Object value) {
		this.val = (Boolean) value;
	}

}
