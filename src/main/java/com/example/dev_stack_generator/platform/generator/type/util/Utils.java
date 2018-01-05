package com.example.dev_stack_generator.platform.generator.type.util;

public class Utils {

	public static String formatEnumName(String name) {
		String formatName = "";
		if (name != null && name.length() > 0) {
			formatName = name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		return formatName;
	}
}
