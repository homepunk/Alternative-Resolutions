package homepunk.alternativeresolutions.presentation.viewmodels;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class CriteriaValuation {

    private char name;
    private int valuation;
    private int lineIndex;
    private int columnIndex;

    public CriteriaValuation() {
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

    @Valuations
    public int getValuation() {
        return valuation;
    }

    public void setValuation(@Valuations int valuation) {
        this.valuation = valuation;
    }
}
