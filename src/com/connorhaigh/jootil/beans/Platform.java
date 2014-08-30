package com.connorhaigh.jootil.beans;

public enum Platform
{
	WINDOWS("Windows"), MACINTOSH("Macintosh OS X"), LINUX("Linux"), UNKNOWN("Unknown");
	
	/**
	 * Creates a new platform definition.
	 * @param name the friendly name of the platform
	 */
	Platform(String name)
	{
		this.name = name;
	}
	
	/**
	 * Returns a string representation of this object.
	 */
	@Override
	public String toString()
	{
		return this.name;
	}
	
	/**
	 * Returns the friendly name of this platform.
	 * @return the friendly name
	 */
	public String getName()
	{
		return this.name;
	}
	
	private String name;
}
