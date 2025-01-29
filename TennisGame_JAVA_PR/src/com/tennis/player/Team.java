package com.tennis.player;

import java.util.ArrayList;
import java.util.List;

public class Team {

	private List<Player> players;
    
    private int game;
	private int set;
	private int score;
	private boolean advantage;

 
	public Team() {
		this.players = new ArrayList<>();
		this.game = 0;
		this.set = 0;
		this.score = 0;
		this.advantage = false; 
		
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}

	public Player getPlayer(String name){

		for(Player player : players)
		{
			if(player.getName().equals(name)){
				return player;
			}
		}
		return null;
	}
	
	
	public String getPlayerNames() {
        StringBuilder names = new StringBuilder();
        for (Player player : players) {
            names.append(player.getName()).append(" ");
        }
        return names.toString().trim();
    }
	
	public void printPlayer() {
		System.out.println("선수 목록 : ");
		for(Player player : players) {
			System.out.println(players);
		}
		
	}
	
	
	

	public void setAdvantage(boolean advantage) {
		// TODO Auto-generated method stub
		this.advantage = advantage;
		
	}
	public boolean isAdvantage() {
		// TODO Auto-generated method stub
		return advantage;
	}
	public void setGame(int game) {
		// TODO Auto-generated method stub
		this.game = game;
		
	}
	public void setSet(int set) {
		// TODO Auto-generated method stub
		this.set = set;
		
	}
	public int getSet() {
		// TODO Auto-generated method stub
		return set;
	}
	public int getGame() {
		// TODO Auto-generated method stub
		return game;
	}
    

	public void setScore(int score){
		this.score = score;
		
	}

	public int getScore(){
		return score;
	}

	

}
