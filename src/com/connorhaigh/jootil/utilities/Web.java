package com.connorhaigh.jootil.utilities;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Web 
{
	/**
	 * Loads and retrieves the contents of a given web page.
	 * @param url the URL to retrieve
	 * @return the contents of the web page
	 * @throws IOException if the web page could not be reached or read
	 */
	public static String retrieve(URL url) throws IOException
	{
		//create connection
		HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
		httpUrlConnection.setDoInput(true);
		httpUrlConnection.setDoOutput(false);
		httpUrlConnection.setUseCaches(false);
		
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream())))
		{
			//result
			StringBuilder result = new StringBuilder();
			
			//read
			String line = null;
			while ((line = bufferedReader.readLine()) != null)
				result.append(line + System.lineSeparator());
			
			return result.toString();
		}
	}
	
	/**
	 * Opens the default Web browser to navigate to the specified page.
	 * @param page the page
	 */
	public static void openBrowser(String page)
	{
		try
		{
			//open browser
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(new URL(page).toURI());
		}
		catch (Exception exception)
		{
			
		}
	}
}
