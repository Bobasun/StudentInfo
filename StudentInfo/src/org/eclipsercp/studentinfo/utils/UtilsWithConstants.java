package org.eclipsercp.studentinfo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.eclipsercp.studentinfo.actions.NewNodeAction;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;

public class UtilsWithConstants {

	public static final String NEW_ITEM = "icons/add.png";
	public static final String NEW_FOLDER = "icons/folder_add.png";
	public static final String DELETE = "icons/cross.png";
	public static final String SAVE = "icons/disk.png";
	public static final String SAVE_ALL = "icons/disk_multiple.png";
	public static final String OPEN = "icons/folder.png";
	public static final String ITEM = "icons/eclipse16.png";
	public static final String OPTION = "icons/sample.png";
	

	public static Properties getProperties(String id) {

		Properties properties = new Properties();
		try {
			if (id.equals(NewNodeAction.ID_ITEM)) {
				properties.load(new FileInputStream("C:\\Users\\H235736\\git\\StudentInfo\\StudentInfo\\config\\item.properties"));
			} else if (id.equals(NewNodeAction.ID_GROUP)) {
				properties.load(new FileInputStream("C:\\Users\\H235736\\git\\StudentInfo\\StudentInfo\\config\\group.properties"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;

	}
}
