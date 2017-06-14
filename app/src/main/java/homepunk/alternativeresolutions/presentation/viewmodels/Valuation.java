package homepunk.alternativeresolutions.presentation.viewmodels;

import android.support.annotation.NonNull;

import homepunk.alternativeresolutions.presentation.utils.interfaces.ValuationRatings;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class Valuation implements Comparable<Valuation> {
    @ValuationRatings
    private int valuationRating;
    private char prefix;
    private int lineIndex;
    private int columnIndex;

    public Valuation() {
        this.prefix = 'k';
    }

    public char getPrefix() {
        return prefix;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    @ValuationRatings
    public int getValuationRating() {
        return valuationRating;
    }

    public void setValuationRating(@ValuationRatings int valuationRating) {
        this.valuationRating = valuationRating;
    }

    public String getFullName() {
        return new StringBuilder()
                .append(prefix)
                .append(columnIndex)
                .append(lineIndex)
                .toString();
    }

    @Override
    public int compareTo(@NonNull Valuation other) {
        int otherLineIndex = other.getLineIndex();
        int otherColumnIndex = other.getColumnIndex();

        if (columnIndex > otherColumnIndex) {
            return 1;
        }

        if (columnIndex < otherColumnIndex) {
            return -1;
        }

        if (columnIndex == otherColumnIndex) {
            if (lineIndex > otherLineIndex) {
                return 1;
            } else {
                return -1;
            }
        }

        return 0;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(prefix)
                .append(columnIndex)
                .append(lineIndex)
                .toString();
    }
}
