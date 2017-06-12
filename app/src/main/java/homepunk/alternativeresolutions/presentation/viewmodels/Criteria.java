package homepunk.alternativeresolutions.presentation.viewmodels;

import java.util.ArrayList;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class Criteria {
    private String name;
    private int index;
    private ArrayList<CriteriaValuation> criteriaValuations;

    public Criteria() {
        name = "K";
        criteriaValuations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<CriteriaValuation> getCriteriaValuations() {
        return criteriaValuations;
    }

    public void setCriteriaValuations(ArrayList<CriteriaValuation> criteriaValuations) {
        this.criteriaValuations = criteriaValuations;
    }

    public void addCriteriaValuation(CriteriaValuation criteriaValuation) {
            criteriaValuations.add(criteriaValuation);
    }

    public void removeCriteriaValuation(CriteriaValuation criteriaValuation) {
        if (criteriaValuations.size() > 0) {
            criteriaValuations.remove(criteriaValuation);
        }
    }
}
