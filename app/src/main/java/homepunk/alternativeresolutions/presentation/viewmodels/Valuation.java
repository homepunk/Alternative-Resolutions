package homepunk.alternativeresolutions.presentation.viewmodels;

import homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class Valuation {
    @Valuations private int valuation;
    private char name;
    private int lineIndex;
    private int columnIndex;

    public Valuation() {
        this.name = 'k';
    }

    public char getName() {
        return name;
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

    @Valuations public int getValuation() {
        return valuation;
    }

    public void setValuation(@Valuations int valuation) {
        this.valuation = valuation;
    }
}
