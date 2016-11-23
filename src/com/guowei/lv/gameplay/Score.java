package com.guowei.lv.gameplay;

public class Score {
    private int inPosition;
    private int inCode;

    public Score(int inPosition, int inCode) {
        this.inPosition = inPosition;
        this.inCode = inCode;
    }

    public int getInPosition() {
        return inPosition;
    }

    public int getInCode() {
        return inCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (inPosition != score.inPosition) return false;
        return inCode == score.inCode;

    }

    @Override
    public int hashCode() {
        int result = inPosition;
        result = 31 * result + inCode;
        return result;
    }

    @Override
    public String toString() {
        return "Score{" +
                "inPosition=" + inPosition +
                ", inCode=" + inCode +
                '}';
    }
}
