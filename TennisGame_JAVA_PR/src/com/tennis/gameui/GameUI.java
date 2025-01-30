package com.tennis.gameui;

import com.tennis.player.Team;
import com.tennis.score.Score;

import java.util.Scanner;

import com.tennis.player.Player;

public class GameUI {

/* 
    
    public static void teamPrintScore(Team team1, Team team2, Score teamScore){


        System.out.println("게임의 현재 점수");
        System.out.printf("팀 1 : (%s) : %d점 || 팀 2 : (%s) : %d점 ", 
            team1.getPlayerNames(), team1.getScore(),
            team2.getPlayerNames(), team2.getScore());

        if (team1.isAdvantage()) {
                System.out.println("팀 1이 어드밴티지를 가졌습니다!");
        } else if (team2.isAdvantage()) {
                System.out.println("팀 2가 어드밴티지를 가졌습니다!");
        }
        if (teamScore.teamDeuce())  {
            System.out.println("듀스 상태!");
        }
    }
    public static void soloPrintScore(Team player1, Team player2, Score soloScore){


        System.out.println("게임의 현재 점수");
        System.out.printf("플레이어 1 : (%s) : %d점 || 플레이어 2 : (%s) : %d점 ", 
            player1.getPlayerNames(), player1.getScore(),
            player2.getPlayerNames(), player2.getScore());

        if (player1.isAdvantage()) {
                System.out.println("플레이어 1이 어드밴티지를 가졌습니다!");
        } else if (player2.isAdvantage()) {
                System.out.println("플레이어 2가 어드밴티지를 가졌습니다!");
        }
        if (soloScore.soloDeuce())  {
            System.out.println("듀스 상태!");
        }
    }


    public static void printTennisCourt(Team team1, Team team2, boolean isDouble) {
        System.out.println("\n 테니스 코트 ");

        if (isDouble) {
            System.out.println(
                "┌──────────────────┐\n" +
                "│  O  |      |  O  │  " + team1.getPlayerNames() + "\n" +
                "│-----|------|-----│\n" +
                "│     |      |     │\n" +
                "│-----|------|-----|\n" +
                "│  O  |      |  O  │  " + team2.getPlayerNames() + "\n" +
                "└──────────────────┘"
        );
        } else {
            System.out.println(
                "┌──────────────────┐\n" +
                "│     |  O   |     │  " + team1.getPlayerNames() + "\n" +
                "│-----|------|-----│\n" +
                "│     |      |     │\n" +
                "│-----|------|-----|\n" +
                "│     |  O   |     │  " + team2.getPlayerNames() + "\n" +
                "└──────────────────┘"
        );
        }
    }
    public static void swapCourt(Team team1, Team team2) {
        System.out.println("\n코트 체인지 ");
        System.out.println(
                "┌──────────────────┐\n" +
                "│     |  O   |     │  " + team1.getPlayerNames() + "\n" +
                "│-----|------|-----│\n" +
                "│     |      |     │\n" +
                "│-----|------|-----|\n" +
                "│     |  O   |     │  " + team2.getPlayerNames() + "\n" +
                "└──────────────────┘"
        );
    }

    /* 
    private static void choiceGameMode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("게임 모드를 선택하세요! (1) 단식 | (2) 복식");
        int gameMode = sc.nextInt();

        if (gameMode == 1) {
            isDouble = false; // 단식
        } else if (gameMode == 2) {
            isDouble = true; // 복식
        } else {
            System.out.println("잘못된 입력입니다. 1 또는 2를 입력하세요.");
            choiceGameMode(); // 다시 입력받기
        }
    }
  */


    // 🎾 점수 출력
    public static void printScore(Score scoreManager) {
        System.out.println("\n[ 현재 점수 ]");
        System.out.println(scoreManager.getScore());
    }

    // 🎾 단식 경기 점수 출력
    public static void soloPrintScore(Team player1, Team player2, Score soloScore) {
        System.out.printf("\n[ 단식 경기 점수 ]\n%s: %d점 | %s: %d점\n",
                player1.getName(), player1.getTeamScore(),
                player2.getName(), player2.getTeamScore());

        if (player1.isAdvantage()) {
            System.out.println("👉 " + player1.getName() + " 어드밴티지!");
        } else if (player2.isAdvantage()) {
            System.out.println("👉 " + player2.getName() + " 어드밴티지!");
        }
        if (soloScore.isGameWon()) {
            System.out.println("🏆 경기 종료! 승리자: " + (player1.getTeamScore() > player2.getTeamScore() ? player1.getName() : player2.getName()));
        }
    }

    // 🎾 복식 경기 점수 출력
    public static void teamPrintScore(Team team1, Team team2, Score teamScore) {
        System.out.printf("\n[ 복식 경기 점수 ]\n%s 팀: %d점 | %s 팀: %d점\n",
                team1.getName(), team1.getTeamScore(),
                team2.getName(), team2.getTeamScore());

        if (team1.isAdvantage()) {
            System.out.println("👉 " + team1.getName() + " 어드밴티지!");
        } else if (team2.isAdvantage()) {
            System.out.println("👉 " + team2.getName() + " 어드밴티지!");
        }
        if (teamScore.isGameWon()) {
            System.out.println("🏆 경기 종료! 승리 팀: " + (team1.getTeamScore() > team2.getTeamScore() ? team1.getName() : team2.getName()));
        }
    }

    // 🎾 테니스 코트 상태 출력
    public static void printTennisCourt(Team team1, Team team2, boolean isDouble) {
        System.out.println("\n🎾 [ 테니스 코트 상태 ]");

        if (isDouble) {
            System.out.println(
                    "┌──────────────────┐\n" +
                    "│  O  |      |  O  │  " + team1.getName() + "\n" +
                    "│-----|------|-----│\n" +
                    "│     |      |     │\n" +
                    "│-----|------|-----|\n" +
                    "│  O  |      |  O  │  " + team2.getName() + "\n" +
                    "└──────────────────┘"
            );
        } else {
            System.out.println(
                    "┌──────────────────┐\n" +
                    "│     |  O   |     │  " + team1.getName() + "\n" +
                    "│-----|------|-----│\n" +
                    "│     |      |     │\n" +
                    "│-----|------|-----|\n" +
                    "│     |  O   |     │  " + team2.getName() + "\n" +
                    "└──────────────────┘"
            );
        }
    }

    // 🎾 코트 체인지 표시
    public static void swapCourt(Team team1, Team team2) {
        System.out.println("\n🔄 코트 체인지!");
        System.out.println(
                "┌──────────────────┐\n" +
                "│     |  O   |     │  " + team1.getName() + "\n" +
                "│-----|------|-----│\n" +
                "│     |      |     │\n" +
                "│-----|------|-----|\n" +
                "│     |  O   |     │  " + team2.getName() + "\n" +
                "└──────────────────┘"
        );
    }
}
