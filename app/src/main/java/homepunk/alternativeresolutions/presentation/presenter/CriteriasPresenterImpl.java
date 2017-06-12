package homepunk.alternativeresolutions.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import homepunk.alternativeresolutions.presentation.base.BasePresenter;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.CriteriasPresenter;
import homepunk.alternativeresolutions.presentation.view.CriteriasView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.CriteriaValuation;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuations;

import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_BAD;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_GOOD;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_THE_BEST;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_THE_WORST;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class CriteriasPresenterImpl extends BasePresenter<CriteriasView> implements CriteriasPresenter {
    public static final int MIN_CRITERIAS_NUMBER = 2;
    private List<Criteria> criteriaList;
    private String[] criteriasNames = new String[]{
            "K1",
            "K2",
            "K3",
            "K4"
    };
    @Valuations private int[] valuations = new int[] {
            VALUATION_THE_WORST,
            VALUATION_BAD,
            VALUATION_GOOD,
            VALUATION_THE_BEST
    };

    private String[] valuationCoefficientDescription = new String[]{
            "не важен",
            "не очень важен",
            "важен",
            "очень важен"
    };

    public CriteriasPresenterImpl() {
        criteriaList = new ArrayList<>(4);
    }

    @Override
    public void onCriteriaQuantitySelected(int selectedNumber) {
        createCriteria(criteriasNames[0]);
        createCriteria(criteriasNames[1]);
        createCriteria(criteriasNames[2]);

        for (Criteria criteria : criteriaList) {
            view.addCriteria(criteria);
        }

        if (selectedNumber > MIN_CRITERIAS_NUMBER) {
            int numberToIterate = selectedNumber - criteriaList.size() + MIN_CRITERIAS_NUMBER;

            if (criteriaList.size() < selectedNumber) {
                for (int i = MIN_CRITERIAS_NUMBER; i < numberToIterate; i++) {
                    createCriteria(criteriasNames[i]);
//                    view.addCriteria();
                }

//            } else if (criteriaList.size() > selectedNumber) {
//                for (int i = criteriaList.size(); i > numberToIterate; i--) {
//                    removeCriteria(criteriaList.get(i));
//                    view.removeCriteria(i);
//                }
            }
        }
    }

    private void createCriteria(String name) {
        Criteria criteria = new Criteria();
        criteria.setName(name);

        for (int i = 0; i < 4; i++) {
            CriteriaValuation criteriaValuation = new CriteriaValuation();
            criteriaValuation.setValuation(valuations[i]);
            criteriaValuation.setLineIndex(i);

            criteria.addCriteriaValuation(criteriaValuation);
        }

        criteriaList.add(criteria);
    }

    private void removeCriteria(Criteria criteria) {
        criteriaList.remove(criteria);
    }

    private boolean isCriteriaAlreadyExists(Criteria criteria) {
        return criteriaList.contains(criteria) || criteriaList.size() == 0;
    }
}
