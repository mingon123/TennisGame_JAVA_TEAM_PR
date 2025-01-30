package com.tennis.gameplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tennis.gameui.Print;
import com.tennis.input.Input;
import com.tennis.input.bak_Input_20250130;
import com.tennis.player.Player;
import com.tennis.player.Team;
import com.tennis.score.Score;

public class GamePlaying {


	public List<Player> players;
	public int currentSet;
	public int currentGame;

	public GamePlaying() {
		Input input = new Input();
		System.out.println("(1)gameplayng  ");
		String gameType = input.gameTypeSelector(); // 단식 복식 선택
		System.out.println("(2)gameplayng  ");
		int gameCount = input.gameCountSelector(gameType); // 게임 성별에 따른 수 선택
		System.out.println("(3)gameplayng  ");
		int matchesToWin = (gameCount == 3) ? 2 : 3; //3경기는 2선승 5경기는 3선승
		List<Player> players = input.playerSetting(gameType); // 플레이어 선택 일단은 이름 직접입력하는법으로 적용
		
		System.out.println("(4)gameplayng  ");
		Team[] teamInfo = input.teamSetting(players); // players를 team배열로 반환
		Team team1 = teamInfo[0];// A팀
		System.out.println("(5)team1  "+ team1);
		Team team2 = teamInfo[1];//B팀
		System.out.println("(6)team2  "+ team2);
		Score scoreManager = new Score(team1, team2); // 적절하게 초기화
		System.out.println("(7)scoreManager  "+ scoreManager);
		gamePlay(matchesToWin, scoreManager, gameType);
	}

	// GamePlaying.java

	public void gamePlay(int matchesToWin, Score scoreManager, String gameType) {
		//	    gameUI.printCourt(team1, team2); // team1 team2 정보 출력 (코트상에서)
		int setsWonTeam1 = 0;
		int setsWonTeam2 = 0;
		Team team1 = scoreManager.getTeam1();
		Team team2 = scoreManager.getTeam2();
		Scanner sc = new Scanner(System.in);


		while (setsWonTeam1 < matchesToWin && setsWonTeam2 < matchesToWin) {
			// 점수 초기화
			scoreManager.resetScores();

			//이부분에 코트정보 표시하면될듯

			// 세트 진행
			while (!scoreManager.isGameWon()) {
				//	        	chooseGame(team1,team2, scoreManager, sc); // 테스트용 선택해서 점수올리는메서드
				playGame(team1, team2, scoreManager, gameType); // 실전용 랜덤으로 Player점수 올라가는 메서드
				if (!scoreManager.isGameWon()) {
					printScore(scoreManager.getScore());
					// 사용자가 키를 입력할 때까지 대기 랜덤돌릴땐 아래 주석 풀면됨
					try {
						System.out.println("아무키나 입력");
						System.in.read();
						System.in.skip(System.in.available());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			// 승리한 팀 계산
			if (scoreManager.getScore().contains("승리")) {
				if (scoreManager.getScore().contains(team1.getName())) {
					setsWonTeam1++;
				} else {
					setsWonTeam2++;
				}
				System.out.printf("세트 스코어: %d - %d%n", setsWonTeam1, setsWonTeam2);
				scoreManager.resetScores(); // 다음 세트를 위해 점수 초기화
				System.out.println("세트 종료 엔터키 입력");
				try {
					System.in.read();
					System.in.skip(System.in.available());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 게임 종결 메시지
		System.out.println("경기 종료");
		System.out.printf("최종 승자: %s (%d세트 승리)\n", setsWonTeam1 > setsWonTeam2 ? team1.getName() : team2.getName(), Math.max(setsWonTeam1, setsWonTeam2));
	}
	public void chooseGame(Team team1, Team team2, Score scoreManager, Scanner sc) { // 테스트용 
		String tmp = sc.nextLine();
		System.out.println("A입력 A팀 B입력 B팀 득점 테스트용");
		if(tmp.equals("A")) {
			scoreManager.pointToPlayer(team1.getPlayers().get(0)); 
		}else {
			scoreManager.pointToPlayer(team2.getPlayers().get(0)); 
		}
	}

	public void playGame(Team team1, Team team2, Score scoreManager, String gameType) {
		int rnd = new Random().nextInt(101);
		if(gameType.equals("단식")) {
			if(rnd > 50) {
				scoreManager.pointToPlayer(team1.getPlayers().get(0));  //A팀 0번플레이어 득점
			}else {
				scoreManager.pointToPlayer(team2.getPlayers().get(0));  // B팀 1번플레이어 득점
			}
		}else {
			if(rnd < 25) {
				scoreManager.pointToPlayer(team1.getPlayers().get(0));//A팀 0번플레이어 득점
			}else if(rnd < 50) {
				scoreManager.pointToPlayer(team1.getPlayers().get(1));//A팀 1번플레이어 득점
			}else if(rnd < 75) {
				scoreManager.pointToPlayer(team2.getPlayers().get(0)); // B팀 0번플레이어 득점
			}else {
				scoreManager.pointToPlayer(team2.getPlayers().get(1)); // B팀 1번플레이어 득점
			}
		}
	}
	private void printScore(String score) {
		System.out.println("\n 점수 :");
		System.out.println(score);
	}

	public void GamePlaying() {
		// TODO Auto-generated method stub
		System.out.println("gggggggggggggggggg");
		
	}


}