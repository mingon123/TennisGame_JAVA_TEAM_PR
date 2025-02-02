package Tennis;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int teamName;
    private List<Player> players;

    public Team(int teamName) {
        this.teamName = teamName;
        this.players = new ArrayList<>();
    }

    // 선수 추가 (최대 2명까지 허용)
    public boolean addPlayer(Player player, MatchGender gender) {
        if (players.size() < 2) {
            if (!players.isEmpty() && gender.equals(MatchGender.MIXED) && players.get(0).getGender() == player.getGender()) {
            	// 팀에 이미 선수가 있는지 확인 && 단/복/혼 확인 && 첫번째 선수와 같은 성별인지 확인 == 새로 추가하는 선수의 성별과 같다면 추가 불가(False) 
                System.out.println("혼합 팀이어야 합니다. 성별이 다른 선수를 추가하세요.");
                return false;
            } 
            else if (!players.isEmpty() && players.get(0).getName().equals(player.getName()) ) {
            	System.out.println("이미 팀에 포함된 선수 입니다. 다른 선수를 추가하세요.");
            	return false;
            }
            players.add(player);
            System.out.println("추가 성공");
            return true;
        } else {
            System.out.println("팀에 추가할 수 있는 최대 인원은 2명입니다.");
            return false;
        }
    }

    // 팀 이름 반환
    public int getTeamName() {
        return teamName;
    }

    // 선수 목록 반환
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("팀 이름: ").append(teamName).append(" ");
        sb.append("선수 목록: ");
        for (Player player : players) {
            sb.append(player).append(" ");
        }
        return sb.toString();
    }
}
