package com.connorhaigh.jootil.beans;

public class Command
{
	/**
	 * Creates a new command.
	 * @param action the action
	 * @param description the description of the command
	 */
	public Command(Action action, String description)
	{
		this.action = action;
		this.description = description;
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
	 * Returns the description of this command.
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	private Action action;
	private String description;
}