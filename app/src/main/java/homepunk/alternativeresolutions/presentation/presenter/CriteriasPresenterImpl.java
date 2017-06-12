package homepunk.alternativeresolutions.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import homepunk.alternativeresolutions.presentation.base.BasePresenter;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.CriteriasPresenter;
import homepunk.alternativeresolutions.presentation.view.CriteriasView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.CriteriaValuation;
import homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations;

import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_BAD;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_GOOD;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_THE_BEST;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_THE_WORST;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class CriteriasPresenterImpl extends BasePresenter<CriteriasView> implements CriteriasPresenter {
    @Valuations
    private int[] valuations;
    private List<Criteria> criterias;

    public CriteriasPresenterImpl() {
        criterias = new ArrayList<>(4);
        this.valuations = new int[]{
                VALUATION_THE_WORST,
                VALUATION_BAD,
                VALUATION_GOOD,
                VALUATION_THE_BEST
        };
    }

    @Override
    public void onCriteriaQuantitySelected(int selectedNumber) {
        if (selectedNumber > criterias.size()) {
            int difference = selectedNumber - criterias.size();

            for (int i = 0; i < difference; i++) {
                createCriteria();
            }
        }

        if (selectedNumber < criterias.size()) {
            for (Criteria criteria : criterias) {
                if (criteria.getIndex() > selectedNumber) {
                    removeCriteria(criteria);
                }
            }
        }
    }

    private void createCriteria() {
        Criteria criteria = new Criteria();
        int newIndex = criterias.size() + 1;
        criteria.setIndex(newIndex);

        for (int i = 0; i < 4; i++) {
            CriteriaValuation criteriaValuation = new CriteriaValuation();
            criteriaValuation.setValuation(valuations[i]);
            criteriaValuation.setColumnIndex(newIndex);
            criteriaValuation.setLineIndex(i);

            criteria.addCriteriaValuation(criteriaValuation);
        }

        criterias.add(criteria);
        view.addCriteria(criteria);
    }

    private void removeCriteria(Criteria criteria) {
        criterias.remove(criteria);
        view.removeCriteria(criteria.getIndex() - 1);
    }
}
