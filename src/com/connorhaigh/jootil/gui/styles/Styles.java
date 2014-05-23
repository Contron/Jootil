package com.connorhaigh.jootil.gui.styles;

public class Styles 
{
	/**
	 * Create a single string from different style rules.
	 * @param rules the style rules
	 * @return the single string
	 */
	public static String build(String... rules)
	{
		return String.join(" ", rules);
	}
	
	public static final String CONTROL_BACKGROUND = "-fx-background-color: white;";
	
	public static final String CONTROL_BORDER_COLOUR = "-fx-border-color: gray;";
	
	public static final String CONTROL_ALL_BORDER = "-fx-border-width: 1 1 1 1;";
	public static final String CONTROL_TOP_BORDER = "-fx-border-width: 1 0 0 0;";
	public static final String CONTROL_BOTTOM_BORDER = "-fx-border-width: 0 0 1 0;";
	public static final String CONTROL_TOP_BOTTOM_BORDER = "-fx-border-width: 1 0 1 0;";
	public static final String CONTROL_LEFT_RIGHT_BORDER = "-fx-border-width: 0 1 0 1;";
}
