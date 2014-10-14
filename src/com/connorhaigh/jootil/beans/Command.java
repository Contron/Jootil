package com.connorhaigh.jootil.beans;

public class Command
{
	/**
	 * Creates a new command.
	 * @param action the action
	 * @param description the description of the command
	 */
	public Command(String name, String description, Action<String> action)
	{
		this.name = name;
		this.description = description;
		
		this.action = action;
	}
	
	/**
	 * Executes this command.
	 * @return the output
	 */
	public String execute()
	{
		return this.action.run();
	}
	
	/**
	 * Returns the name of this command.
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Returns the description of this command.
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	private String name;
	private String description;
	
	private Action<String> action;
}