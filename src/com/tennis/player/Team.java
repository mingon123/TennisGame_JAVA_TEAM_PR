package com.tennis.player;
import java.util.List;
import java.util.stream.Collectors;

// 테니스 경기를 하는 팀
// 팀에 속한 플레이어들의 점수와 상태를 관리하는 역할

public class Team {

   private List<Player> players; // 팀에 속한 선수들의 목록
   private int score; // 팀의 총 점수
   private String name; // 팀의 이름
   // 팀이 어드밴티지 상태인지 여부
   // 점수 차가 1점인 상황에서 우세한 팀이 있다
   private boolean isAdvantage; 
   
   public boolean isAdvantage() {
      return isAdvantage;
   }
   public void setAdvantage(boolean isAdvantage) {
      this.isAdvantage = isAdvantage;
   }
   
   // 팀 클래스 생성자는 name과 players를 매개변수로 받아서 팀 객체 초기화
   // 생성자
   public Team(String name, List<Player> players) {
      super();   
      this.name = name;
      this.players = players;
      this.score = 0;
      isAdvantage = false;
   }
   // 클래스의 속성들을 접근하고 수정할 수 있는 메서드
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
   // 각 플레이어의 점수를 합산해서 팀의 총 점수 계산
   //  p.getScore :  각 플레이어의 점수 가져오는 방법
   public int getTeamScore() {
      int scoreSum = 0;
      for(Player p : players) {
         scoreSum += p.getScore();
      }
      return scoreSum;
   }
   public void setTeamScore(int teamScore) {
      this.score = teamScore;
   }
   
   // 팀의 점수 초기화 메서드
   public void resetTeamScore() {
      //this.score = 0;
      //this.isAdvantage = false;
      for (Player player : players) {
         player.resetScore();
      }
      this.isAdvantage = false;
   }
   
   // 추가 -----------------------------------------------------------------------------------------------------
   public Object getPlayerNames() {
      if (players == null || players.isEmpty()) {
            return "선수 없음";
        }
        return players.stream()
                      .map(Player::getName) // Player 객체에서 이름만 추출
                      .collect(Collectors.joining(", ")); // ", "로 구분된 문자열 생성
    
   }
   // 이 메서드는 팀에 속한 선수들의 이름을 하나의 문자열로 반환하는 기능을 한다
   // 추가 -----------------------------------------------------------------------------------------------------
   public Object getScore() {
      // TODO Auto-generated method stub
      return null;
   }
}