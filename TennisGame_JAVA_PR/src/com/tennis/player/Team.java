package com.tennis.player;
import java.util.List;

public class Team {
	
	private List<Player> players;
	private int teamScore;
	private String name;
	private boolean isAdvantage;
	
	public boolean isAdvantage() {
		return isAdvantage;
	}
	public void setAdvantage(boolean isAdvantage) {
		this.isAdvantage = isAdvantage;
	}
	public Team(String name, List<Player> players) {
		super();	
		this.name = name;
		this.players = players;
		this.teamScore = 0;
		isAdvantage = false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getTeamScore() {
		int scoreSum = 0;
		for(Player p : players) {
			scoreSum += p.getScore();
		}
		return scoreSum;
	}
	public void setTeamScore(int teamScore) {
		this.teamScore = teamScore;
	}
	
	public void addPlayer(String name) {
		this.players.add(new Player(name));
	}
	
	public void increaseTeamScore() {
		this.teamScore++;
	}
	public void resetTeamScore() {
		this.teamScore = 0;
		this.isAdvantage = false;
		for (Player player : players) {
            player.resetScore();
        }
	}
}