package project1;

public class TennisGameService {
	private final ScoreCalculator scoreCalculator;
	private String[][] players;
	private int maxSets;
	private int gameType;

	public TennisGameService() {
		this.scoreCalculator = new ScoreCalculator();
	}

	public void startGame() {
		initializeGame();
		displayInitialMatchup();
		playMatch();
	}

	private void initializeGame() {
		System.out.println("🎾 테니스 게임을 시작합니다!");
		Player player = new Player();
		this.maxSets = player.getValidatedInput("경기 유형 선택 (3세트: 1, 5세트: 2): ", 1, 2) == 1 ? 3 : 5;
		this.scoreCalculator.setRequiredSets(this.maxSets == 3 ? 2 : 3);
		this.gameType = player.getValidatedInput("게임 모드 선택 (단식: 1, 복식: 2): ", 1, 2);
		this.players = player.getPlayerNames(gameType);
	}

	private void displayInitialMatchup() {
		if (gameType == 1) {
			System.out.println("플레이어 1: " + players[0][0] + " vs 플레이어 2: " + players[1][0]);
		} else {
			System.out.printf("팀 1: %s, %s vs 팀 2: %s, %s%n", players[0][0], players[0][1], players[1][0],
					players[1][1]);
		}
	}

	private void playMatch() {
		while (!scoreCalculator.isMatchOver()) {
			System.out.println("\n현재 스코어: " + scoreCalculator.getScore());
			int winner = getWinner();
			scoreCalculator.addPoint(winner - 1);
		}
		displayFinalResult();
	}

	private int getWinner() {
		Player player = new Player();
		if (gameType == 1) {
			return player.getValidatedInput(String.format("포인트 획득 선수 (1: %s, 2: %s): ", players[0][0], players[1][0]),
					1, 2);
		}
		return player.getValidatedInput(String.format("포인트 획득 팀 (1: %s & %s, 2: %s & %s): ", players[0][0],
				players[0][1], players[1][0], players[1][1]), 1, 2);
	}

	private void displayFinalResult() {
		int matchWinner = scoreCalculator.getMatchWinner();
		System.out.println("\n🏆 경기 종료! " + (gameType == 1 ? "우승자: " + players[matchWinner][0]
				: String.format("우승팀: %s & %s", players[matchWinner][0], players[matchWinner][1])));
		System.out.println("최종 스코어: " + scoreCalculator.getScore());
	}

	public static void main(String[] args) {
		TennisGameService gameService = new TennisGameService();
		gameService.startGame();
	}
}