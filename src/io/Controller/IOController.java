package io.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import io.Model.Game;
import io.view.IOFrame;

public class IOController
{

	private IOFrame appFrame;
	
	private ArrayList<Game> projectGames;
	{
		
	}
	public IOController()
	{
		projectGames = new ArrayList<Game>();
	}
	
	public ArrayList<Game> getProjectGames()
	{
		return projectGames;
	}

	public void start()
	{
		appFrame = new IOFrame(this);
	}
	
	public Game readGameInformation()
	{
		String fileName = "save file.txt"; //without a path it will look to the directory of the project!
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		
		Game currentGame = null;
		int gameRanking = 0;
		String gameTitle = "";
		ArrayList<String> gameRules = new ArrayList<String>();
		
		try
		{
			fileReader = new Scanner(currentSaveFile);
			
			gameTitle = fileReader.nextLine();
			gameRanking = fileReader.nextInt();
			
			while(fileReader.hasNext())
			{
				gameRules.add(fileReader.nextLine());
			}	
			
			currentGame = new Game(gameRules, gameRanking, gameTitle);
			fileReader.close();
		}
		catch(FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
		}
		
		return currentGame;
	}
	
	private String readAllGameInformation()
	{
		String fileContents = "";
		String fileName = "save file.txt";
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		
		try
		{
			fileReader = new Scanner(currentSaveFile);
			while(fileReader.hasNext())
			{
				fileContents += fileReader.nextLine();
			}
			fileReader.close();
		}
		catch(FileNotFoundException fileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, fileDoesNotExist.getMessage());
		}
		return fileContents;
		
	}
	
	private void convertTextToGames(String currentInfo)
	{
		String [] gameChunks = currentInfo.split(";");
		for(String currentBlock : gameChunks)
		{
			int currentIndex = currentBlock.indexOf("\n");
			String title = currentBlock.substring(0, currentIndex);
			int nextIndex = currentBlock.indexOf("\n", currentIndex);
			String ranking = currentBlock.substring(currentIndex+1, nextIndex);
			String rules = currentBlock.substring(nextIndex+1);
			Game currentGame = makeGameFromInput(title, ranking, rules);
			projectGames.add(currentGame);
		}
	}
	
	public Game pickRandomGameFromSaveFile()
	{
		Game currentGame = null;
		
		String allInfo = readAllGameInformation();
		convertTextToGames(allInfo);
		int randomIndex = (int)(Math.random() * (double) projectGames.size());
		currentGame = projectGames.get(randomIndex);
		
		return currentGame;
	}
		
	public Game makeGameFromInput(String gameTitle, String gameRanking, String gameRules)
	{
		Game currentGame = new Game();
		currentGame.setGameTitle(gameTitle);
		
		if(checkNumberFormat(gameRanking))
		{
			currentGame.setFunRanking(Integer.parseInt(gameRanking));
		}
		else
		{
			return null;
		}
		
		String[] temp = gameRules.split("\n");
		ArrayList<String> tempRules = new ArrayList<String>();
		
		for(String tempWord : temp)
		{
			tempRules.add(tempWord);
		}
		currentGame.setGameRules(tempRules);
		
		return currentGame;
	}
	
	private boolean checkNumberFormat(String toBeParsed)
	{
		boolean isNumber = false;
		
		try
		{
			int valid = Integer.parseInt(toBeParsed);
			isNumber = true;
		}
		catch(NumberFormatException error)
		{
			JOptionPane.showMessageDialog(appFrame, "Please try agagin with an actual number for the ranking.");
		}
		
		return isNumber;
	}
	
	public void saveGameInformation(Game currentGame)
	{
		PrintWriter gameWriter;
		String saveFile = "save file.txt";
		
		try
		{
			gameWriter = new PrintWriter(saveFile); //creates the save file.
			
			gameWriter.append(currentGame.getGameTitle() + "\n");
			gameWriter.append(currentGame.getFunRanking() + "\n");
			for(int count = 0; count < currentGame.getGameRules().size(); count++)
			{
				gameWriter.append(currentGame.getGameRules().get(count) + "\n");
			}
			
			gameWriter.append(";" + "\n");
			
			gameWriter.close(); // required to prevent corruption of data from outside sources
		}
		catch(FileNotFoundException noFileExists)
		{
			JOptionPane.showMessageDialog(appFrame, "Could not create the save file. :( ");
			JOptionPane.showMessageDialog(appFrame, noFileExists.getMessage());
		}
		
	}
}
