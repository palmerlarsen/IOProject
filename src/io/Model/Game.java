package io.Model;

import java.util.ArrayList;

/**
 * the Game superclass for the IO project.
 * @author plar4927
 *@version 1.0 12/13/2013 added data members, constructors and default play method.
 */
public class Game
{

	private ArrayList<String> gameRules;
	private int funRanking;
	private String gameTitle;
	
	public Game()
	{
		gameRules = new ArrayList<String>();
		funRanking = 0;
		gameTitle = "";
	}
	
	public Game(ArrayList<String> gameRUles, int funRanking, String gameTitle)
	{
		this.gameRules = gameRUles;
		this.funRanking = funRanking;
		this.gameTitle = gameTitle;
	}
	
	public ArrayList<String> getGameRules()
	{
		return gameRules;
	}

	public void setGameRules(ArrayList<String> gameRules)
	{
		this.gameRules = gameRules;
	}

	public int getFunRanking()
	{
		return funRanking;
	}

	public void setFunRanking(int funRanking)
	{
		this.funRanking = funRanking;
	}

	public String getGameTitle()
	{
		return gameTitle;
	}

	public void setGameTitle(String gameTitle)
	{
		this.gameTitle = gameTitle;
	}

	public void play()
	{
		
	}
	
}
