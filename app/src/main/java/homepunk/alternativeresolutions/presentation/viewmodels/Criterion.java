package homepunk.alternativeresolutions.presentation.viewmodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class Criterion implements Serializable {
    private char prefix;
    private int index;
    private List<Valuation> valuations;

    public Criterion() {
        prefix = 'K';
        valuations = new ArrayList<>();
    }

    public char getPrefix() {
        return prefix;
    }

    public String getFullName() {
        return new StringBuilder()
                .append(prefix)
                .append(index)
                .toString();
    }

    public int getArrayIndex() {
        return index - 1;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Valuation> getValuations() {
        return valuations;
    }

    public void setValuations(List<Valuation> valuations) {
        this.valuations = valuations;
    }

    public void addValuation(Valuation valuation) {
        valuations.add(valuation);
    }

    public void removeValuation(Valuation valuation) {
        if (valuations.size() > 0) {
            valuations.remove(valuation);
        }
    }

    public void sortValuations() {
        Collections.sort(valuations, (o1, o2) -> Integer.compare(o1.getValuationRating(), o2.getValuationRating()));
    }
}
