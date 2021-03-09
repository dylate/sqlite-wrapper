package query.utils;

public class SqlFormatter {
	public static String value(String value) {
		if (isNumber(value)) {
			return value;
		} else {
			return String.format("'%s'", value);
		}
	}
	
	private static boolean isNumber(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
