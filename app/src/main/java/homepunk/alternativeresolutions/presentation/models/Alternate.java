package homepunk.alternativeresolutions.presentation.models;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import java.io.Serializable;

/**
 * Created by homepunk on 6/14/17.
 */

public class Alternate implements Comparable<Alternate>, Serializable {
    private Valuation first;
    private Valuation second;
    private Pair<Valuation, Valuation> alternate;

    public Alternate() {}

    public Alternate(Valuation first, Valuation second) {
        this.alternate = new Pair<>(first, second);
    }

    public void setFirstValuation(Valuation first) {
        if (second != null) {
            alternate = new Pair<>(first, second);
        }

        this.first = first;
    }

    public void setSecondValuation(Valuation second) {
        if (first != null) {
            alternate = new Pair<>(first, second);
        }

        this.second = second;
    }

    public Pair<Valuation, Valuation> getAlternate() {
        return alternate;
    }

    public void setAlternate(Pair<Valuation, Valuation> alternate) {
        this.alternate = alternate;
    }

    public boolean isEmpty() {
        return first == null || second == null;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(alternate.first.getFullName())
                .append(" - ")
                .append(alternate.second.getFullName())
                .toString();
    }

    @Override
    public int compareTo(@NonNull Alternate other) {
        Valuation first = alternate.first;
        Valuation second = alternate.second;
        Valuation otherFirst = other.alternate.first;
        Valuation otherSecond = other.alternate.second;

        if (first.compareTo(otherFirst) == 0) {
            return second.compareTo(otherSecond);
        } else if (first.compareTo(otherFirst) < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
