package project1;

public class ScoreCalculator {
	private final int[] points = { 0, 0 };
	private final int[] games = { 0, 0 };
	private final int[] sets = { 0, 0 };
	private int advantagePlayer = -1;
	private int requiredSets = 2; // 기본값

	public void setRequiredSets(int required) {
		this.requiredSets = required;
	}

	public String getScore() {
		return String.format("포인트: %s | 게임: %s | 세트: %d-%d", formatPoints(), formatGames(), sets[0], sets[1]);
	}

	public void addPoint(int player) {
		int opponent = 1 - player;

		if (points[player] < 30) {
			points[player] = points[player] == 0 ? 15 : 30;
		} else if (points[player] == 30) {
			points[player] = 40;
		} else if (points[opponent] == 40) {
			handleDeuce(player);
		} else {
			winGame(player);
		}
	}

	private void handleDeuce(int player) {
		int opponent = 1 - player;
		if (advantagePlayer == player) {
			winGame(player);
		} else if (advantagePlayer == opponent) {
			advantagePlayer = -1;
			points[0] = points[1] = 40;
		} else {
			advantagePlayer = player;
		}
	}

	private void winGame(int player) {
		games[player]++;
		resetGameScores();

		if (checkSetWinner(player)) {
			sets[player]++;
			resetGameWins();
		}
	}

	private void resetGameScores() {
		points[0] = points[1] = 0;
		advantagePlayer = -1;
	}

	private boolean isGameDeuce() {
		return games[0] >= 5 && games[1] >= 5;
	}

	private boolean checkSetWinner(int player) {
		int opponent = 1 - player;
		if (isGameDeuce()) {
			// 듀스 상황에서는 2게임 차이가 나야 승리
			return games[player] >= 5 && (games[player] - games[opponent] >= 2);
		}
		// 일반 상황에서는 6게임 이상이고 2게임 차이면 승리
		return (games[player] >= 6 && games[player] - games[opponent] >= 2);
	}

	private void resetGameWins() {
		games[0] = games[1] = 0;
	}

	public boolean isMatchOver() {
		return sets[0] >= requiredSets || sets[1] >= requiredSets;
	}

	public int getMatchWinner() {
		if (sets[0] >= requiredSets)
			return 0;
		if (sets[1] >= requiredSets)
			return 1;
		return -1;
	}

	private String formatPoints() {
		if (points[0] == 40 && points[1] == 40) {
			return advantagePlayer == -1 ? "40-40 (Deuce)" : (advantagePlayer == 0 ? "Adv-P1" : "Adv-P2");
		}
		return points[0] + "-" + points[1];
	}

	private String formatGames() {
		String baseScore = games[0] + "-" + games[1];
		if (isGameDeuce()) {
			if (games[0] > games[1]) {
				return baseScore + " (Adv-P1)";
			} else if (games[1] > games[0]) {
				return baseScore + " (Adv-P2)";
			} else {
				return baseScore + " (Deuce)";
			}
		}
		return baseScore;
	}
}