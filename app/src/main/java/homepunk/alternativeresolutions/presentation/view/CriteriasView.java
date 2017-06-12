package homepunk.alternativeresolutions.presentation.view;

import java.util.List;

import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.CriteriaValuation;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public interface CriteriasView extends View {
    void addCriteria(Criteria criteria);

    void removeCriteria(int idnex);

    void onCriteriasEntered(List<CriteriaValuation> criteriaValuations);

    void onCriteriaNumberEntered(int criteriaScalesNumber);

    void onCriteriasEnterFailed(String error);
}
