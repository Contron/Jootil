package com.connorhaigh.jootil.utilities;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Fonts 
{
	public static final Font MONOSPACED_FONT = Font.font("Monospaced");
	
	public static final Font LARGE_FONT = Font.font(18);
	public static final Font BOLD_FONT = Font.font(Font.getDefault().getName(), FontWeight.BOLD, Font.getDefault().getSize());
	public static final Font ITALIC_FONT = Font.font(Font.getDefault().getName(), FontPosture.ITALIC, Font.getDefault().getSize());
}
