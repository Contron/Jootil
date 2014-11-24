package com.connorhaigh.jootil.core;

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
	 * Returns a string representation of this platform.
	 */
	@Override
	public String toString()
	{
		return this.name;
	}

	private String name;
}
