package homepunk.alternativeresolutions.presentation.view;

import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public interface CriteriasView extends View {
    void addCriteria(Criteria criteria);

    void removeCriteria(Criteria criteria);

    void addCriteriaValuation(Criteria criteria, Valuation valuation);

    void removeCriteriaValuation(Criteria criteria, Valuation valuation);

}
