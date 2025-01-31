
package com.tennis.gameui;

import java.util.Scanner;

import com.tennis.gameplay.GamePlaying;
import com.tennis.input.Input;
import com.tennis.player.Team;

public class Print {

	public static void printMain() {

		System.out.println("====================================");
		System.out.println("            테니스 게임");
		System.out.println("====================================");
		System.out.println("Made by. 1조");
		System.out.println();
		System.out.println("\t1. 테니스 경기시작");
		System.out.println("\t2. 종료");
		System.out.print("\n\t입력 : ");

	}

	public static void printRuleSelect() {

		// System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("=====================================");
		System.out.println("        게임 규칙을 선택해주세요!");
		System.out.println("=====================================");

	}

	public static void printPlayerSelect() {
		// System.out.println("\n\n\n\n\n");
		System.out.println("=====================================");
		System.out.println("           선수 선택해주세요! ");
		System.out.println("=====================================");
	}

	// 코트 상태 추가
	// -------------------------------------------------------------------------------------------------
	public static void printCourtInfo(Team team1, Team team2, boolean isLeftCourt, boolean isLeftServer) {
		System.out.println("=====================================");
		System.out.println("            현재 코트 상태");
		System.out.println("=====================================");

		if (team1 != null && team2 != null) {
			System.out.printf("현재 서브 팀: %s\n", isLeftServer ? team1.getName() : team2.getName());
			System.out.printf("현재 코트 방향: %s\n", isLeftCourt ? "왼쪽" : "오른쪽");
		} else {
			System.out.println("팀 정보가 올바르게 설정되지 않았습니다.");
		}

		System.out.println("=====================================");
	}

	// 코트 상태 추가
	// -------------------------------------------------------------------------------------------------

	// 구성원 확인 코드
	// 추가----------------------------------------------------------------------------------------------
	public static void printPlayerTeam(Team team1, Team team2) {
		System.out.println("\n=====================================");
		System.out.println("             팀 구성");
		System.out.println("=====================================");
		System.out.println("A팀: " + team1.getPlayerNames());
		System.out.println("B팀: " + team2.getPlayerNames());
		System.out.println("=====================================\n");
	}
	// 구성원 확인 코드
	// 추가----------------------------------------------------------------------------------------------

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int startMenu = 0;

		while (true) {
			Print.printMain();
			startMenu = sc.nextInt();
			if (startMenu == 1)
				break; // 게임시작
			else if (startMenu == 2)
				System.exit(0);
		}

		Input input = new Input();
		GamePlaying gamePlaying = new GamePlaying();

	}// main
}// class