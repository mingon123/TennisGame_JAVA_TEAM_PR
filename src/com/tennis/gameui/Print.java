package com.tennis.gameui;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.tennis.gameplay.GamePlaying;
import com.tennis.input.Input;
import com.tennis.player.Player;

public class Print {
	public static void printMain() {
		System.out.println("====================================");
		System.out.println("            테니스 게임");
		System.out.println("====================================");
		System.out.println("Made by. 1조");
		System.out.println();
		System.out.println("\t1. 테니스 경기시작");
		System.out.println("\t2. 플레이어 선택");
		System.out.println("\t3. 종료");
		System.out.print("\n\t입력 : ");
	}
	
	public static void printRuleSelect() {
		//System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("=====================================");
		System.out.println("        게임 규칙을 선택해주세요!");
		System.out.println("====================================="); 
		System.out.println("cccccc  " );
	}
	
	public static void printPlayerSelect() {
		System.out.println("bbbbbb  " );
		// System.out.println("\n\n\n\n\n");
		System.out.println("=====================================");
		System.out.println("           선수 선택해주세요! ");
		System.out.println("=====================================");

	
		
		// System.out.println(Input.malePlayers.get(0));
		// System.out.println(Input.malePlayers.get(1));
		// System.out.println( "       선수번호 입력: ");
	}

	
	public static void printScoreBoard(){
		
	}
	
	
	
	public static void main(String[] args) {
	    
	    Scanner sc = new Scanner(System.in);
	    int startMenu = 0;

	    while(true) {
			Print.printMain();
			startMenu = sc.nextInt();
			if(startMenu == 1) break; //게임시작
			else if(startMenu == 2); // 플레이어 생성
			else if(startMenu == 3) System.exit(0);
		}
	    
	    Input input = new Input();
	    GamePlaying GamePlaying = new GamePlaying();
	    
	    
		String gameType=input.gameTypeSelector(); 
		List<Player> selectedPlayers22 =input.playerSetting(gameType);
		
		System.out.println("yy   : ");
		System.out.println("selectedPlayers : " + selectedPlayers22);

		GamePlaying.GamePlaying();
		
		}// main
}//class
