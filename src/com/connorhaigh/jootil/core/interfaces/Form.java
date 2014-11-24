package com.connorhaigh.jootil.core.interfaces;

public interface Form 
{
	/**
	 * Populates the fields for this form.
	 */
	public void populateFields();
	
	/**	
	 * Confirms the contents of this form.
	 */
	public void contentsConfirmed();
}
