package org.eclipsercp.studentinfo.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.RootNode;

public class NodeTransfer extends ByteArrayTransfer {

	private static NodeTransfer instance = new NodeTransfer();
	private static final String TYPE_NAME = "node-transfer";
	private static final int TYPEID = registerType(TYPE_NAME);

	public static NodeTransfer getInstance() {
		return instance;
	}

	private NodeTransfer() {
	}

	@Override
	public TransferData[] getSupportedTypes() {
		int[] types = getTypeIds();
		TransferData[] data = new TransferData[types.length];
		for (int i = 0; i < types.length; i++) {
			data[i] = new TransferData();
			data[i].type = types[i];
			data[i].formatetc = new FORMATETC();
			data[i].formatetc.cfFormat = types[i];
			data[i].formatetc.dwAspect = COM.DVASPECT_CONTENT;
			data[i].formatetc.lindex = -1;
			data[i].formatetc.tymed = COM.TYMED_HGLOBAL;
		}
		return data;
	}

	@Override
	public boolean isSupportedType(TransferData transferData) {
		return super.isSupportedType(transferData);
	}

	@Override
	protected int[] getTypeIds() {
		return new int[] { TYPEID };
	}

	@Override
	protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}

	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		byte[] bytes = toByteArray((INode) object);
		if (bytes != null)
			super.javaToNative(bytes, transferData);
	}

	private byte[] toByteArray(INode object) {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = null;
		byte[] bytes = null;
		try {
			try {
				objOut = new ObjectOutputStream(byteOut);
				objOut.writeObject(object);
				objOut.flush();
				bytes = byteOut.toByteArray();
			} catch (IOException e) {
				System.err.println("error node Transfer (open out stream)");
			}
		} finally {
			try {
				byteOut.close();
			} catch (IOException e) {
				System.err.println("error node Transfer (close out stream)");
			}
		}
		return bytes;
	}

	@Override
	protected Object nativeToJava(TransferData transferData) {
		byte[] bytes = (byte[]) super.nativeToJava(transferData);
		return fromByteArray(bytes);
	}

	protected INode fromByteArray(byte[] bytes) {
		Object object = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
			object = in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("");

		}
		return (INode) object;
	}

	@Override
	protected boolean validate(Object object) {
		System.err.println("valis");
		return !(object instanceof RootNode);

	}

}
