package com.connorhaigh.jootil.gui;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.connorhaigh.jootil.beans.Action;
import com.connorhaigh.jootil.beans.Command;
import com.connorhaigh.jootil.utilities.Files;
import com.connorhaigh.jootil.utilities.Resources;

public class DeveloperConsoleStage extends Stage
{
	/**
	 * Creates a new developer console stage.
	 * @param owner the owner of the stage
	 */
	public static void showDeveloperConsoleStage(Window owner)
	{
		//show
		DeveloperConsoleStage developerConsoleStage = new DeveloperConsoleStage(owner);
		developerConsoleStage.show();
	}
	
	/**
	 * Creates a new developer console stage.
	 * @param owner the owner of the stage
	 */
	public DeveloperConsoleStage(Window owner)
	{
		this.commands = new ArrayList<Command>();
		this.history = new ArrayList<String>();
		this.historyIndex = -1;
		
		//setup stage
		this.initOwner(owner);
		this.setTitle("Developer Console");
		this.setMinWidth(200);
		this.setMinHeight(200);
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
		this.logTextArea.setFont(Resources.MONOSPACED_FONT);
		vbox.getChildren().add(this.logTextArea);
		VBox.setVgrow(this.logTextArea, Priority.ALWAYS);
		
		//input text field
		this.inputTextField = new TextField();
		this.inputTextField.setPrefColumnCount(50);
		this.inputTextField.setFont(Resources.MONOSPACED_FONT);
		this.inputTextField.setOnAction(event -> this.executeCommand());
		this.inputTextField.setOnKeyPressed(event ->
		{
			//navigate
			KeyCode keyCode = event.getCode();
			
			if (keyCode == KeyCode.UP)
				this.navigateHistory(1);
			else if (keyCode == KeyCode.DOWN)
				this.navigateHistory(-1);
		});
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
		//default commands
		this.commands.add(new Command("help", "Displays the list of commands", () -> this.listHelp()));
		this.commands.add(new Command("clear", "Clears the console text", () -> this.clearConsole()));
		this.commands.add(new Command("close", "Closes the console", () -> this.closeConsole()));
		this.commands.add(new Command("version", "Displays the current runtime version", () -> this.showVersion()));
		this.commands.add(new Command("exit", "Exits the platform", () -> this.exitPlatform()));
		this.commands.add(new Command("memory", "Collects and displays memory statistics", () -> this.collectMemory()));
		this.commands.add(new Command("dumpProperties", "Dumps all system properties",  () -> this.dumpProperties()));
		this.commands.add(new Command("dumpEnvironmentVariables", "Dumps all environment variables", () -> this.dumpEnvironmentVariables()));
		this.commands.add(new Command("runGarbageCollector", "Runs the garbage collector", () -> this.runGarbageCollector()));
		
		//header text
		this.logTextArea.clear();
		this.logTextArea.appendText("Developer console initialised\n");
		this.logTextArea.appendText("Type 'help' for available commands\n\n");
	}
	
	/**
	 * Registers a command with this console.
	 * @param command the command
	 */
	public void registerCommand(Command command)
	{
		//add
		this.commands.add(command);
	}
	
	/**
	 * Registers a command with this console with a runnable.
	 * @param name the name of the command
	 * @param description the description of the command
	 * @param runnable the runnable to execute for this command
	 */
	public void registerCommand(String name, String description, Runnable runnable)
	{
		Action action = () ->
		{
			//run
			runnable.run();
			
			return "Command executed successfully";
		};
		
		//register
		this.registerCommand(new Command(name, description, action));
	}
	
	/**
	 * Executes the current command.
	 */
	private void executeCommand()
	{
		//get text
		String name = this.inputTextField.getText().trim();
		this.logTextArea.appendText("> " + name + "\n");
		this.inputTextField.clear();
		
		//add to history
		this.history.add(0, name);
		this.historyIndex = -1;
		
		//get command
		Command command = this.commands.stream()
			.filter(c -> c.getName().equals(name))
			.findFirst()
			.orElse(null);
		
		if (command == null)
		{
			//unknown
			this.logTextArea.appendText("Unknown command\n");
			
			return;
		}
		
		//execute
		this.logTextArea.appendText(command.execute() + "\n");
	}
	
	/**
	 * Navigate through the command history.
	 * @param increment the increment
	 */
	private void navigateHistory(int increment)
	{
		//change
		int newIndex = (this.historyIndex + increment);
		if (newIndex <= -1 || newIndex >= this.history.size())
			return;
		
		//set
		this.historyIndex = newIndex;
		this.inputTextField.setText(this.history.get(this.historyIndex));
		this.inputTextField.selectEnd();
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
		for (Command command : this.commands)
			result.append(command.getName() + ": " + command.getDescription() + "\n");
		
		//total
		result.append("\n" + this.commands.size() + " total commands");
		
		return result.toString();
	}
	
	/**
	 * Clear the console.
	 * @return the output
	 */
	private String clearConsole()
	{
		this.logTextArea.clear();
		
		return "Cleared the console\n";
	}
	
	/**
	 * Close the console.
	 * @return the output
	 */
	private String closeConsole()
	{
		//close
		this.close();
		
		return "Closed the console";
	}
	
	/**
	 * Display the version of the Java runtime.
	 * @return the version
	 */
	private String showVersion()
	{
		return String.format
		(
			"Java %s (%s), on %s (version %s)", 
			System.getProperty("java.version"), 
			System.getProperty("java.vendor"), 
			System.getProperty("os.name"), 
			System.getProperty("os.version")
		);
	}
	
	/**
	 * Exit the platform.
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
	
	private ArrayList<Command> commands;
	private ArrayList<String> history;
	private int historyIndex;
	
	private TextArea logTextArea;
	private TextField inputTextField;
}
