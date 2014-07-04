package com.connorhaigh.jootil.utilities;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Files 
{
	/**
	 * Returns the friendly size of a file.
	 * @param size the size of the file
	 * @return the friendly size
	 */
	public static String getSize(long size)
	{
		//check
		if (size < 1024)
			return (size + " bytes");
		else if (size < (1024 * 1024))
			return ((size / 1024) + " KB");
		else if (size < (1024 * 1024 * 1024))
			return (Files.SMALL_DECIMAL_FORMAT.format((double) size / (1024 * 1024)) + " MB");
		else
			return (Files.LARGE_DECIMAL_FORMAT.format((double) size / (1024 * 1024 * 1024)) + " GB");
	}
	
	/**
	 * Returns the name of a full-path file.
	 * @param path the path
	 * @return the name of the file
	 */
	public static String getName(String path)
	{
		//get slash index
		int slashIndex = path.lastIndexOf('/');
		if (slashIndex == -1)
			return path;
		
		return path.substring(slashIndex + 1);
	}
	
	/**
	 * Returns the friendly name of a file.
	 * @param path the file
	 * @return the friendly name of the file
	 */
	public static String getFriendlyName(File file)
	{
		//get name
		String name = file.getName();
		
		//get dot index
		int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1)
			return name;
		
		return name.substring(0, dotIndex);
	}
	
	/**
	 * Returns the extension of a file.
	 * @param file the file name
	 * @return the extension of the file
	 */
	public static String getExtension(String file)
	{
		//get dot index
		int dotIndex = file.indexOf('.');
		if (dotIndex == -1)
			return file;
		
		return file.substring(dotIndex + 1);
	}
	
	/**
	 * Returns the remaining time for a file transfer.
	 * @param current the current bytes transferred
	 * @param total the total bytes to transfer
	 * @param speed the speed of the transfer
	 * @return the remaining time
	 */
	public static String getRemainingTime(int current, int total, int speed)
	{
		//result
		StringBuilder result = new StringBuilder();
		
		//get time
		int time = ((total - current) / speed);
		int hours = (time / 3600);
		int minutes = (time / 60);
		int seconds = (time % 60);
		
		//append result
		if (hours > 0)
			result.append(hours + " hours, ");
		if (minutes > 0)
			result.append(minutes + " minutes, ");
		result.append(seconds + " seconds");
		
		return result.toString();
	}
	
	/**
	 * Reads a text file fully.
	 * @param file the file to read
	 * @return the contents
	 * @throws IOException if the file could not be read
	 */
	public static String readText(File file) throws IOException
	{
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file))))
		{
			//result
			StringBuilder result = new StringBuilder();
			String line = null;
			
			//read
			while ((line = bufferedReader.readLine()) != null)
				result.append(line + System.lineSeparator());
			
			return result.toString();
		}
	}
	
	/**
	 * Reads a binary file fully.
	 * @param file the file to read
	 * @return the contents
	 * @throws IOException if the file could not be read
	 */
	public static byte[] readBinary(File file) throws IOException
	{
		try (FileInputStream fileInputStream = new FileInputStream(file); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream())
		{
			//read
			byte[] buffer = new byte[4096];
			int bytes = 0;
			while ((bytes = fileInputStream.read(buffer)) != -1)
				byteArrayOutputStream.write(buffer, 0, bytes);
			
			return byteArrayOutputStream.toByteArray();
		}
	}
	
	public static final DecimalFormat SMALL_DECIMAL_FORMAT = new DecimalFormat("##.00");
	public static final DecimalFormat LARGE_DECIMAL_FORMAT = new DecimalFormat("##.0");
}
