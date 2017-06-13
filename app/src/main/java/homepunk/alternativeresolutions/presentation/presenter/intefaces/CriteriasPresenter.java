package homepunk.alternativeresolutions.presentation.presenter.intefaces;

import homepunk.alternativeresolutions.presentation.view.CriteriasView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public interface CriteriasPresenter extends Presenter<CriteriasView> {
    void onCriteriaNumberSelected(int number);

    void onAddCriteriaValuationClick(Criteria criteria);

    void onCriteriaValuationClick(Criteria criteria, int position);
}
