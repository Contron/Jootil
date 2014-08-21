package com.connorhaigh.jootil.utilities;

import com.connorhaigh.jootil.beans.Platform;

public class OperatingSystem 
{
	/**
	 * Returns the platform for the current operating system.
	 * @return the platform
	 */
	public static Platform getPlatform()
	{
		//get name
		String osName = System.getProperty("os.name").toLowerCase();
		
		if (osName.contains("windows"))
			return Platform.WINDOWS;
		else if (osName.contains("mac"))
			return Platform.MACINTOSH;
		else if (osName.contains("linux") || osName.contains("unix"))
			return Platform.LINUX;
		else
			return Platform.UNKNOWN;
	}
}
