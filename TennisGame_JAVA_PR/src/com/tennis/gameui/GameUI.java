package com.tennis.gameui;

import com.tennis.player.Team;

public class GameUI {


    public static void printScore(Team team1, Team team2){

        System.out.println("게임의 현재 점수");
        System.out.printf("팀 1 : (%s) : %d점 || 팀 2 : (%s) : %d점 ", 
            team1.getPlayerNames(), team1.getScore(),
            team2.getPlayerNames(), team2.getScore());

        if (team1.isAdvantage()) {
                System.out.println("팀 1이 어드밴티지를 가졌습니다!");
        } else if (team2.isAdvantage()) {
                System.out.println("팀 2가 어드밴티지를 가졌습니다!");
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
    public static void swapCour(Team team1, Team team2) {
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

}