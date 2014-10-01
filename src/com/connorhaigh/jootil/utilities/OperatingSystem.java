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
		{
			return Platform.WINDOWS;
		}
		else if (osName.contains("mac"))
		{
			return Platform.MACINTOSH;
		}
		else if (osName.contains("linux") || osName.contains("unix"))
		{
			return Platform.LINUX;
		}
		else
		{
			return Platform.UNKNOWN;
		}
	}
	
	/**
	 * Returns if the current operating system is Windows.
	 * @return if the current operating system is Windows
	 */
	public static boolean isWindows()
	{
		return (OperatingSystem.getPlatform() == Platform.WINDOWS);
	}
	
	/**
	 * Returns if the current operating system is Mac OS X.
	 * @return if the current operating system is Mac OS X
	 */
	public static boolean isMacintosh()
	{
		return (OperatingSystem.getPlatform() == Platform.MACINTOSH);
	}
	
	/**
	 * Returns if the current operating system is based on a Linux distribution.
	 * @return if the current operating system is based on a Linux distribution
	 */
	public static boolean isLinux()
	{
		return (OperatingSystem.getPlatform() == Platform.LINUX);
	}
	
	/**
	 * Returns if the current operating system is unknown.
	 * @return if the current operating system is unknown
	 */
	public static boolean isUnknown()
	{
		return (OperatingSystem.getPlatform() == Platform.UNKNOWN);
	}
}
