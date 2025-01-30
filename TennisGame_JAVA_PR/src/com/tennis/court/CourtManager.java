package com.tennis.court;
import java.util.Random;
import com.tennis.player.Team;


public class CourtManager {

    private int totalGamePlay = 0;
    private boolean isLeftCourt = true; // 현재 코트 위치 (true: 왼쪽, false: 오른쪽)
    private boolean isLeftServer = true; // 현재 서브 위치
    private Team servingTeam; // 현재 서브하는 팀 (추가)
    private static final Random random = new Random();

    // 코트 체인지 기능
    public void swapCourt(Team team1, Team team2) {
        if (totalGamePlay % 2 == 1) { // 홀수 게임마다 코트 변경
            isLeftCourt = !isLeftCourt;
        }
    }

    // 라인 아웃 처리
    public boolean isLineOut() {
        return random.nextDouble() < 0.05; // 5% 확률로 라인 아웃
       
    }

    // 서브 위치 관리
    public void swapServePos() {
        isLeftServer = !isLeftServer;
        
    }


    // 게임 진행 시 호출할 함수
    public void updateCourtState(Team team1, Team team2) {
        totalGamePlay++;
        swapServePos(); // 서브 위치 변경
        swapCourt(team1, team2); // 코트 체인지
    }

    // 추가된 부분 --------------------------------------------------
 
    // 새로운 세트가 시작될 때 서브 팀 변경하는 메서드 추가
    public void swapServeAtSetStart(Team team1, Team team2) {
        servingTeam = (servingTeam == team1) ? team2 : team1;
        System.out.println("새 세트 시작! 서브 팀 변경: " + servingTeam.getName());
    }

    // 현재 서브하는 팀을 설정하는 메서드 추가
    public void setInitialServer(Team team) {
        servingTeam = team;
        System.out.println("첫 번째 서브 팀: " + servingTeam.getName());
    }

    // 현재 서브하는 팀을 반환
    public Team getServingTeam() {
        return servingTeam;
    }

    // 추가된 부분 --------------------------------------------------

       // 현재 코트 정보 반환
       public boolean isLeftCourt() {
        return isLeftCourt;
    }

    public boolean isLeftServer() {
        return isLeftServer;
    }
}
