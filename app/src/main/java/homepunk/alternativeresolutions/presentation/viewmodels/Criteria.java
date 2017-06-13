package homepunk.alternativeresolutions.presentation.viewmodels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class Criteria {
    private String name;
    private int index;
    private List<Valuation> valuations;

    public Criteria() {
        name = "K";
        valuations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return new StringBuilder()
                .append(name)
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

    public void removeCriteriaValuation(Valuation valuation) {
        if (valuations.size() > 0) {
            valuations.remove(valuation);
        }
    }

    public void sortValuations() {
        Collections.sort(valuations, (o1, o2) -> Integer.compare(o1.getValuation(), o2.getValuation()));
    }
}
