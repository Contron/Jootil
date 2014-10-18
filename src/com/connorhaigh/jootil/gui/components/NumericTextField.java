package com.connorhaigh.jootil.gui.components;

import javafx.scene.control.TextField;

public class NumericTextField extends TextField
{
	/**
	 * Creates a new numeric text field.
	 * @param minimum the minimum value
	 * @param maximum the maximum value
	 */
	public NumericTextField(int minimum, int maximum)
	{
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
	/**
	 * Creates a new numeric text field with the default boundaries.
	 */
	public NumericTextField()
	{
		this(-100, 100);
	}
	
	/**
	 * Replaces text at the specified position.
	 */
    @Override
    public void replaceText(int start, int end, String text)
    {
    	//replace
    	if (this.validate(text))
    		super.replaceText(start, end, text);
    }
    
    /**
     * Replaces a selection of text.
     */
    @Override
    public void replaceSelection(String text)
    {
    	//replace
    	if (this.validate(text))
    		super.replaceSelection(text);
    }
    
    /**
     * Validates the specified text to ensure it matches the criteria.
     * @param text the text to validate
     * @return if the text is valid
     */
    private boolean validate(String text)
    {
    	//empty check
    	if (text.isEmpty())
    		return true;
    	
    	//basic check
    	if (!text.matches("[0-9]"))
    		return false;
    	
    	try
    	{
    		//parse
    		int number = Integer.parseInt(this.getText() + text);
    		
    		return (number >= this.minimum && number <= this.maximum);
    	}
    	catch (NumberFormatException numberFormatException)
    	{
    		return false;
    	}
    }
    
    /**
     * Sets the value of this text field.
     * @param value the value
     */
    public void setValue(int value)
    {
    	this.setText(String.valueOf(value));
    }
    
    /**
     * Returns the value of this text field.
     * @return the value
     */
    public int getValue()
    {
    	return Integer.parseInt(this.getText());
    }
    
    /**
     * Sets the minimum value for this text field.
     * @param minimum the minimum value
     */
    public void setMinimum(int minimum)
    {
    	this.minimum = minimum;
    }
    
    /**
     * Sets the maximum value for this text field.
     * @param maximum the maximum value
     */
    public void setMaximum(int maximum)
    {
    	this.maximum = maximum;
    }
	
	private int minimum;
	private int maximum;
}
