package homepunk.alternativeresolutions.presentation.viewmodels;

import homepunk.alternativeresolutions.presentation.utils.interfaces.ValuationRatings;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class Valuation {
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
}
