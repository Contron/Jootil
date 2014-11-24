package com.connorhaigh.jootil.utilities;

public class Styles 
{
	/**
	 * Creates a single string from different style rules.
	 * @param rules the style rules
	 * @return the single string
	 */
	public static String build(String... rules)
	{
		return String.join(Styles.DELIMITER + " ", rules);
	}
	
	private static final String DELIMITER = ";";
	
	public static final String BACKGROUND_COLOUR = "-fx-background-color: white";
	public static final String TRANSPARENT_BACKGROUND_COLOUR = "-fx-background-color: transparent";
	
	public static final String BORDER_COLOUR = "-fx-border-color: gray";
	
	public static final String ALL_BORDER = "-fx-border-width: 1 1 1 1";
	public static final String TOP_BORDER = "-fx-border-width: 1 0 0 0";
	public static final String LEFT_BORDER = "-fx-border-width: 0 0 0 1";
	public static final String BOTTOM_BORDER = "-fx-border-width: 0 0 1 0";
	public static final String RIGHT_BORDER = "-fx-border-width: 0 1 0 0";
	public static final String TOP_BOTTOM_BORDER = "-fx-border-width: 1 0 1 0";
	public static final String LEFT_RIGHT_BORDER = "-fx-border-width: 0 1 0 1";
}
