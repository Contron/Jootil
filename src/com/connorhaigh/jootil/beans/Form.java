package com.connorhaigh.jootil.beans;

public interface Form 
{
	/**
	 * Called when this stage should initially populate its fields.
	 */
	public void populateFields();
	
	/**
	 * Called when this stage was confirmed (the user clicked OK).
	 */
	public void contentsConfirmed();
}
