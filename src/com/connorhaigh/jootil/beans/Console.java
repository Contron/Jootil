package com.connorhaigh.jootil.beans;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

import javafx.application.Platform;

import com.connorhaigh.jootil.utilities.Files;

public class Console 
{
	/**
	 * Creates a new console.
	 */
	public Console()
	{
		this.commands = new ArrayList<Command>();
		
		//initialise
		this.initialise();
	}
	
	/**
	 * Executes the specified command if it can be found.
	 * @param name the name of the command
	 * @return the output
	 */
	public String execute(String name)
	{
		//get command
		Command command = this.commands.stream()
			.filter(c -> c.getName().equals(name))
			.findFirst()
			.orElse(null);
		
		//check
		if (command == null)
			return "Unknown command";
		
		return command.execute();
	}
	
	/**
	 * Initialises this console with default commands.
	 */
	private void initialise()
	{
		//default commands
		this.commands.add(new Command("help", "Displays the list of commands", () -> this.listHelp()));
		this.commands.add(new Command("version", "Displays the current runtime version", () -> this.showVersion()));
		this.commands.add(new Command("exit", "Exits the platform", () -> this.exitPlatform()));
		this.commands.add(new Command("memory", "Collects and displays memory statistics", () -> this.collectMemory()));
		this.commands.add(new Command("dump_properties", "Dumps all system properties",  () -> this.dumpProperties()));
		this.commands.add(new Command("dump_environment_variables", "Dumps all environment variables", () -> this.dumpEnvironmentVariables()));
		this.commands.add(new Command("run_garbage_collector", "Runs the garbage collector", () -> this.runGarbageCollector()));
		this.commands.add(new Command("enable_instruction_tracing", "Enables instruction tracing", () -> this.enableInstructionTracing()));
		this.commands.add(new Command("enable_method_tracing", "Enables method tracing", () -> this.enableMethodTracing()));
	}
	
	/**
	 * Lists all the commands.
	 * @return the commands
	 */
	private String listHelp()
	{
		//result
		StringBuilder result = new StringBuilder();
		
		//loop
		for (Command command : this.commands)
			result.append(command.getName() + ": " + command.getDescription() + "\n");
		
		//total
		result.append("\n" + this.commands.size() + " total commands");
		
		return result.toString();
	}
	
	/**
	 * Displays the version of the Java runtime.
	 * @return the version
	 */
	private String showVersion()
	{
		return String.format
		(
			"Java %s (%s-bit, from %s), on %s (version %s)",
			System.getProperty("java.version"),
			System.getProperty("sun.arch.data.model"),
			System.getProperty("java.vendor"),
			System.getProperty("os.name"),
			System.getProperty("os.version")
		);
	}
	
	/**
	 * Exits the platform.
	 * @return the output
	 */
	private String exitPlatform()
	{
		//exit
		Platform.exit();
		
		return "Exited the platform";
	}
	
	/**
	 * Runs the garbage collector.
	 * @return the output
	 */
	private String runGarbageCollector()
	{
		//run
		System.gc();
		
		return "Ran the garbage collector successfully";
	}
	
	/**
	 * Collects the memory statistics.
	 * @return the memory statistics
	 */
	private String collectMemory()
	{
		//result
		StringBuilder result = new StringBuilder();
		
		//append memory
		result.append("Used memory: " + Files.getSize(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + "\n");
		result.append("Free memory: " + Files.getSize(Runtime.getRuntime().freeMemory()) + "\n");
		result.append("Total memory: " + Files.getSize(Runtime.getRuntime().totalMemory()) + "\n");
		result.append("Maximum memory: " + Files.getSize(Runtime.getRuntime().maxMemory()));
		
		return result.toString();
	}
	
	/**
	 * Dumps all the current system properties.
	 * @return the output
	 */
	private String dumpProperties()
	{
		//result
		StringBuilder result = new StringBuilder();
		Set<Entry<Object, Object>> entrySet = System.getProperties().entrySet();
		
		//collect
		for (Entry<Object, Object> entry : entrySet)
			result.append(entry.getKey() + ": " + entry.getValue() + "\n");
		
		//total
		result.append("\n" + entrySet.size() + " total properties");
		
		return result.toString();
	}
	
	/**
	 * Dumps all the current environment variables.
	 * @return the output
	 */
	private String dumpEnvironmentVariables()
	{
		//result
		StringBuilder result = new StringBuilder();
		Set<Entry<String, String>> entrySet = System.getenv().entrySet();
		
		//collect
		for (Entry<String, String> entry : entrySet)
			result.append(entry.getKey() + ": " + entry.getValue() + "\n");
		
		//total
		result.append("\n" + entrySet.size() + " total environment variables");
		
		return result.toString();
	}
	
	/**
	 * Enables tracing instructions for this session.
	 * @return the output
	 */
	private String enableInstructionTracing()
	{
		//enable
		Runtime.getRuntime().traceInstructions(true);
		
		return "Enabled instruction tracing for this session";
	}
	
	/**
	 * Enables tracing methods for this session.
	 * @return the output
	 */
	private String enableMethodTracing()
	{
		//enable
		Runtime.getRuntime().traceMethodCalls(true);
		
		return "Enabled method tracing for this session";
	}
	
	/**
	 * Returns the list of commands in this console.
	 * @return the list of commands
	 */
	public ArrayList<Command> getCommands()
	{
		return this.commands;
	}
	
	private ArrayList<Command> commands;
}
