package com.tennis.player;

import java.util.ArrayList;

public class Team {

	

    public ArrayList<Player> players; 
		
	
    private int teamScore;
    private int game;
	private int set;
	private boolean advantage;
	
	
 
	public Team() {
		this.players = new ArrayList<>();
		
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	
	public void printPlayer() {
		System.out.println("리스트 확인 : ");
		for(Player player : players) {
			System.out.println(players);
		}
		
	}
	
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setScore(int i) {
		// TODO Auto-generated method stub
		
	}
	public void setAdvantage(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public boolean isAdvantage() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setGame(int i) {
		// TODO Auto-generated method stub
		
	}
	public void setSet(int i) {
		// TODO Auto-generated method stub
		
	}
	public int getSet() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getGame() {
		// TODO Auto-generated method stub
		return 0;
	}
    


	

}
