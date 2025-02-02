package Tennis;

public enum Score {
    ZERO(0),
    FIFTEEN(1),
    THIRTY(2),
    FORTY(3);

    private final int value;

    Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // 점수 값으로 실제 점수 매핑
    public static int getScoreByValue(int value) {
        switch (value) {
            case 0: return 0;
            case 1: return 15;
            case 2: return 30;
            case 3: return 40;
            default: return 40;
        }
    }
}