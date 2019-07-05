package org.eclipsercp.studentinfo.editor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean validateName(String name) {
		name = name.replaceAll("/", "");
		Pattern validationPattern = Pattern.compile("^.+");
		Matcher matcher = validationPattern.matcher(name);
		return matcher.matches();
	}

	public static boolean validateNumber(String number) {
		Pattern validationPattern = Pattern.compile("(\\d+\\.?)+");
		Matcher matcher = validationPattern.matcher(number);
		return matcher.matches();

	}

}
