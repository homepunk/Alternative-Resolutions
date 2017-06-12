package homepunk.alternativeresolutions.presentation.viewmodels;

import java.util.ArrayList;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class Criteria {
    private String name;
    private ArrayList<CriteriaValuation> criteriaValuations;

    public Criteria() {
        criteriaValuations = new ArrayList<>();
    }

    public Criteria(String name) {
        this.name = name;
    }

    public Criteria(CriteriaValuation... criteriaValuations) {
        this.criteriaValuations = new ArrayList<>();

        for (CriteriaValuation criteriaValuation : criteriaValuations) {
            this.criteriaValuations.add(criteriaValuation);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CriteriaValuation> getCriteriaValuations() {
        return criteriaValuations;
    }

    public void setCriteriaValuations(ArrayList<CriteriaValuation> criteriaValuations) {
        this.criteriaValuations = criteriaValuations;
    }

    public void addCriteriaValuation(CriteriaValuation criteriaValuation) {
        if (criteriaValuations != null) {
            criteriaValuations.add(criteriaValuation);
        }
    }

    public void removeCriteriaValuation(CriteriaValuation criteriaValuation) {
        if (criteriaValuations != null) {
            criteriaValuations.remove(criteriaValuation);
        }
    }
}
