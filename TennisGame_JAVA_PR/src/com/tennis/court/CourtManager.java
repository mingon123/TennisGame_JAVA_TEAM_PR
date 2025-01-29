package com.tennis.court;

import com.tennis.player.Team;
import java.util.Random;

public class CourtManager {
	
    private int totalGamePlay = 0;
    private boolean isLeftCourt = true; // 현재 코트 위치 (true: 왼쪽, false: 오른쪽)
    private boolean isLeftServer = true; // 현재 서브 위치
    private static final Random random = new Random();

    // 코트 체인지 기능
    public void swapCourt(Team team1, Team team2) {
        if (totalGamePlay % 2 == 1) { // 홀수 게임마다 코트 변경
            isLeftCourt = !isLeftCourt;
            System.out.println("\n 코트 체인지! ");
            printCourt(team1, team2);
        }
    }

    // 라인 아웃 처리
    public boolean isLineOut() {
        boolean lineOut = random.nextDouble() < 0.05; // 5% 확률로 라인 아웃
        if (lineOut) {
            System.out.println("라인 아웃! 상대 팀에게 점수 부여!");
        }
        return lineOut;
    }

    // 서브 위치 관리
    public void swapServePos() {
        isLeftServer = !isLeftServer;
        System.out.println("\n서브 위치 변경! 현재 서버 위치: " + (isLeftServer ? "왼쪽" : "오른쪽"));
    }

    // 코트 출력
    public void printCourt(Team team1, Team team2) {
        System.out.println("\n 테니스 코트 ");
        System.out.println(
            (isLeftCourt ? "팀 1" : "팀 2") + "이 왼쪽 코트에 위치\n" +
            (isLeftCourt ? "팀 2" : "팀 1") + "이 오른쪽 코트에 위치\n" +
            "서브 위치: " + (isLeftServer ? "왼쪽" : "오른쪽")
        );
    }

    // 게임 진행 시 호출할 함수
    public void updateCourtState(Team team1, Team team2) {
        totalGamePlay++;
        swapServePos(); // 서브 위치 변경
        swapCourt(team1, team2); // 코트 체인지
    }
}