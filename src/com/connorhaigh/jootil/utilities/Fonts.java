package com.connorhaigh.jootil.utilities;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Fonts 
{
	public static final Font MONOSPACED_FONT = Font.font("Monospaced");
	
	public static final Font LARGE_FONT = Font.font(18);
	public static final Font BOLD_FONT = Font.font(Fonts.DEFAULT_NAME, FontWeight.BOLD, Fonts.DEFAULT_SIZE);
	public static final Font ITALIC_FONT = Font.font(Fonts.DEFAULT_NAME, FontPosture.ITALIC, Fonts.DEFAULT_SIZE);
	
	private static final String DEFAULT_NAME = Font.getDefault().getName();
	private static final double DEFAULT_SIZE = Font.getDefault().getSize();
}
