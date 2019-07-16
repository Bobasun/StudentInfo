package org.eclipsercp.studentinfo.undoredo;

import java.util.Stack;

import org.eclipsercp.studentinfo.model.INode;

public class UndoRedoINodes {

	private Stack<INode> undo;
	private Stack<INode> redo;

	public UndoRedoINodes() {
		undo = new Stack<>();
		redo = new Stack<>();
	}

	public void pushUndo(INode node) {
		undo.add(node);

	}

	public void pushRedo(INode node) {
		redo.add(node);
	}

	public INode popUndo() {
		INode res = undo.pop();
//		redo.push(res);
		return res;
	}

	public INode popRedo() {
		INode res = redo.pop();
//		undo.push(res);
		return res;
	}

	public void clearRedo() {
		redo.clear();
	}

	public boolean hasUndo() {
		return !undo.isEmpty();
	}

	public boolean hasRedo() {
		return !redo.isEmpty();

	}
}