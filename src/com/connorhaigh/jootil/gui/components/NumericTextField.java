package com.connorhaigh.jootil.gui.components;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;

public class NumericTextField extends TextField
{
	/**
	 * Creates a new numeric text field.
	 * @param minimum the minimum value
	 * @param maximum the maximum value
	 */
	public NumericTextField(double minimum, double maximum)
	{
		this.valueProperty = new SimpleDoubleProperty(minimum);
		
		this.minimumProperty = new SimpleDoubleProperty(minimum);
		this.maximumProperty = new SimpleDoubleProperty(maximum);
	}
	
	/**
	 * Creates a new numeric text field with the default boundaries.
	 */
	public NumericTextField()
	{
		this(0, 100);
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
    	
    	try
    	{
    		//parse
    		double number = Double.parseDouble(this.getText() + text);
    		
    		if (number >= this.minimumProperty.get() && number <= this.maximumProperty.get())
    		{
    			//update
    			this.valueProperty.set(number);
    			
    			return true;
    		}
    		else
    		{
    			return false;
    		}
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
    public void setValue(double value)
    {
    	this.valueProperty.set(value);
    }
    
    /**
     * Returns the value of this text field.
     * @return the value
     */
    public double getValue()
    {
    	return this.valueProperty.get();
    }
    
    /**
     * Sets the minimum value for this text field.
     * @param minimum the minimum value
     */
    public void setMinimum(double minimum)
    {
    	this.minimumProperty.set(minimum);
    }
    
    /**
     * Returns the minimum value for this text field.
     * @return the minimum value
     */
    public double getMinimum()
    {
    	return this.minimumProperty.get();
    }
    
    /**
     * Sets the maximum value for this text field.
     * @param maximum the maximum value
     */
    public void setMaximum(double maximum)
    {
    	this.maximumProperty.set(maximum);;
    }
    
    /**
     * Returns the maximum value for this text field.
     * @return the maximum value
     */
    public double getMaximum()
    {
    	return this.maximumProperty.get();
    }
    
    /**
     * Returns the value property.
     * @return the value property
     */
    public SimpleDoubleProperty valueProperty()
    {
    	return this.valueProperty;
    }
    
    /**
     * Returns the minimum property.
     * @return the minimum property
     */
    public SimpleDoubleProperty minimumProperty()
    {
    	return this.minimumProperty;
    }
    
    /**
     * Returns the maximum property.
     * @return the maximum property
     */
    public SimpleDoubleProperty maximumProperty()
    {
    	return this.maximumProperty;
    }
    
    private SimpleDoubleProperty valueProperty;
    
    private SimpleDoubleProperty minimumProperty;
    private SimpleDoubleProperty maximumProperty;
}
