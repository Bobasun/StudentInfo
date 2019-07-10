package studentinfo.states;

import org.eclipse.core.commands.State;

public class StateOption2 extends State {

	public final static String STATE_ID = "StudentInfo.commands.optionGroup.state";
	private Boolean val;

	public StateOption2() {
		val = false;
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
