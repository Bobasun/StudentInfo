package org.eclipsercp.studentinfo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipsercp.studentinfo.actions.NewNodeAction;

public class UtilsWithConstants {

	public static final String NEW_ITEM = "icons/add.png";
	public static final String NEW_FOLDER = "icons/folder_add.png";
	public static final String DELETE = "icons/cross.png";
	public static final String SAVE = "icons/disk.png";
	public static final String SAVE_ALL = "icons/disk_multiple.png";
	public static final String OPEN = "icons/folder.png";
	public static final String ITEM = "icons/file.png";
	public static final String OPTION = "icons/sample.png";
	public static final String DEFAULT_PICTURE = "icons/simpsons_PNG95.png";
	public static final String FOLDER = "icons/folder-red.png";
	public static final String EXIT = "icons/exit.png";
	public static final String ROOT = "icons/root_folder.png";

	public static Properties getProperties(String id) {

		Properties properties = new Properties();
		try {
			if (id.equals(NewNodeAction.ID_ITEM)) {
				properties.load(new FileInputStream(
						"C:\\Users\\H235736\\git\\StudentInfo\\StudentInfo\\config\\item.properties"));
			} else if (id.equals(NewNodeAction.ID_GROUP)) {
				properties.load(new FileInputStream(
						"C:\\Users\\H235736\\git\\StudentInfo\\StudentInfo\\config\\group.properties"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;

	}

	public static Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
//		  image.dispose();
		return scaled;
	}
}
