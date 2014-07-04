package com.connorhaigh.jootil.gui;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.connorhaigh.jootil.utilities.Files;

public class DeveloperConsoleStage extends Stage
{
	/**
	 * Create a new developer console stage.
	 * @param owner the owner of the stage
	 */
	public static void showDeveloperConsoleStage(Window owner)
	{
		//show
		DeveloperConsoleStage developerConsoleStage = new DeveloperConsoleStage(owner);
		developerConsoleStage.show();
	}
	
	/**
	 * Create a new developer console stage.
	 * @param owner the owner of the stage
	 */
	public DeveloperConsoleStage(Window owner)
	{
		this.commands = new HashMap<String, Command>();
		this.commands.put("help", new Command(() -> this.listHelp(), "Displays the list of commands"));
		this.commands.put("clear", new Command(() -> this.clearConsole(), "Clears the console text"));
		this.commands.put("exit", new Command(() -> this.exitConsole(), "Exits the console"));
		this.commands.put("memory", new Command(() -> this.collectMemory(), "Collect memory statistics"));
		this.commands.put("properties", new Command(() -> this.dumpProperties(), "Dumps all system properties"));
		this.commands.put("environment_variables", new Command(() -> this.dumpEnvironmentVariables(), "Dumps all environment variables"));
		this.commands.put("garbage_collector", new Command(() -> this.runGarbageCollector(), "Run the garbage collector"));
		
		//setup stage
		this.initOwner(owner);
		this.setTitle("Developer Console");
		this.getIcons().add(new Image("/images/icons/console.png"));
		
		//root vbox
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setSpacing(10);
		
		//log text area
		this.logTextArea = new TextArea();
		this.logTextArea.setPrefRowCount(15);
		this.logTextArea.setPrefColumnCount(50);
		this.logTextArea.setEditable(false);
		this.logTextArea.setFont(Font.font("Monospaced"));
		vbox.getChildren().add(this.logTextArea);
		VBox.setVgrow(this.logTextArea, Priority.ALWAYS);
		
		//input text field
		this.inputTextField = new TextField();
		this.inputTextField.setPrefColumnCount(50);
		this.inputTextField.setFont(Font.font("Monospaced"));
		this.inputTextField.setOnAction(event -> this.executeCommand());
		vbox.getChildren().add(this.inputTextField);
		
		//initialise
		this.initialiseConsole();
		
		//show
		this.setScene(new Scene(vbox));
		this.sizeToScene();
	}
	
	/**
	 * Initialise the console.
	 */
	public void initialiseConsole()
	{
		//header text
		this.logTextArea.setText("");
		this.logTextArea.appendText("Development console initialised\n");
		this.logTextArea.appendText("Type 'help' for available commands\n\n");
	}
	
	/**
	 * Register a command with this console.
	 * @param name the name of the command
	 * @param command the command
	 */
	public void registerCommand(String name, Command command)
	{
		//add
		this.commands.put(name, command);
	}
	
	/**
	 * Convenience method to register a command with this console with a generic runnable.
	 * @param name the name of the command
	 * @param description the description of the command
	 * @param runnable the runnable to execute for this command
	 */
	public void registerCommand(String name, String description, Runnable runnable)
	{
		//create default action
		Action action = () ->
		{
			//run
			runnable.run();
			
			return "Command executed successfully";
		};
		
		//add command
		this.commands.put(name, new Command(action, description));
	}
	
	/**
	 * Execute the current command.
	 */
	private void executeCommand()
	{
		//get text
		String name = this.inputTextField.getText().trim();
		this.logTextArea.appendText("> " + name + "\n");
		this.inputTextField.setText("");
		
		if (!this.commands.containsKey(name))
		{
			//unknown
			this.logTextArea.appendText("Unknown command\n");
			
			return;
		}
		
		//get command
		Command command = this.commands.get(name);
		String result = command.execute();

		//append
		this.logTextArea.appendText(result + "\n");
	}
	
	/**
	 * List all the commands.
	 * @return the commands
	 */
	private String listHelp()
	{
		//result
		StringBuilder result = new StringBuilder();
		
		//loop
		Set<Entry<String, Command>> entrySet = this.commands.entrySet();
		for (Entry<String, Command> entry : entrySet)
			result.append(entry.getKey() + ": " + entry.getValue().getDescription() + "\n");
		
		//total
		result.append("\n" + entrySet.size() + " total commands");
		
		return result.toString();
	}
	
	/**
	 * Clear the console.
	 * @return the output
	 */
	private String clearConsole()
	{
		//clear
		this.logTextArea.setText("");
		
		return "Cleared the console\n";
	}
	
	/**
	 * Close the console.
	 * @return the output
	 */
	private String exitConsole()
	{
		//close
		this.close();
		
		return "Closed the console";
	}
	
	/**
	 * Run the garbage collector.
	 * @return the output
	 */
	private String runGarbageCollector()
	{
		//run
		System.gc();
		
		return "Ran the garbage collector successfully";
	}
	
	/**
	 * Collect memory statistics.
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
	 * Dump all the current system properties.
	 * @return the output
	 */
	private String dumpProperties()
	{
		//result
		StringBuilder result = new StringBuilder();
		
		//loop
		Set<Entry<Object, Object>> entrySet = System.getProperties().entrySet();
		for (Entry<Object, Object> entry : entrySet)
			result.append(entry.getKey() + ": " + entry.getValue() + "\n");
		
		//total
		result.append("\n" + entrySet.size() + " total properties");
		
		return result.toString();
	}
	
	/**
	 * Dump all the current environment variables.
	 * @return the output
	 */
	private String dumpEnvironmentVariables()
	{
		//result
		StringBuilder result = new StringBuilder();
		
		//loop
		Set<Entry<String, String>> entrySet = System.getenv().entrySet();
		for (Entry<String, String> entry : entrySet)
			result.append(entry.getKey() + ": " + entry.getValue() + "\n");
		
		//total
		result.append("\n" + entrySet.size() + " total environment variables");
		
		return result.toString();
	}
	
	private HashMap<String, Command> commands;
	
	private TextArea logTextArea;
	private TextField inputTextField;
}

class Command
{
	/**
	 * Create a new command.
	 * @param action the action
	 * @param description the description of the command
	 */
	public Command(Action action, String description)
	{
		this.action = action;
		this.description = description;
	}
	
	/**
	 * Execute this command.
	 * @return the output
	 */
	public String execute()
	{
		return this.action.run();
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	private Action action;
	private String description;
}

interface Action
{
	/**
	 * Run this action.
	 * @return the output
	 */
	public String run();
}