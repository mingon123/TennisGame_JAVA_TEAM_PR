package com.tennis.gameplay;

import java.util.Arrays;
import java.util.List;

import com.tennis.player.Player;
import com.tennis.player.Team;

public class GamePlaying {
	//경기흐름 조정하는 부분 main에서는 이부분의 함수만 호출할 예정
	public static void gameStart() {
		//
		playerList(); // 
		
		System.out.printf(Arrays.toString(playerList()));
		game();
		
	}
	private static void game() {
		//
	}
	private static List<Player> playerList() {
		// 이름, 플레이어 등등 등록
		
        
		Player player1 = new Player("권용범", "남");
		Player player2 = new Player("김대원", "남");
		Player player3 = new Player("김민곤", "남");
		Player player4 = new Player("김하은", "여");
		Player player5 = new Player("라일락", "여");
		Player player6 = new Player("박세훈", "남");
		Player player7 = new Player("박예진", "여");
		Player player8 = new Player("박정현", "남");
		Player player9 = new Player("양희동", "남");
		Player player10 = new Player("오재문", "남");
		Player player11 = new Player("유예나", "여");
		Player player12 = new Player("윤형식", "남");
		Player player13 = new Player("이찬희", "남");
		Player player14 = new Player("주정호", "남");
		Player player15 = new Player("최지우", "여");

		
		
		
		// 복식
//		Team team1 = new Team( , );
//		Team team2 = new Team( , );
		
	}
	
}
