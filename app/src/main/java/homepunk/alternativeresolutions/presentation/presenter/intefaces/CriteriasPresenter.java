package homepunk.alternativeresolutions.presentation.presenter.intefaces;

import homepunk.alternativeresolutions.presentation.view.CriteriasView;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public interface CriteriasPresenter extends Presenter<CriteriasView> {
    void onCriteriaQuantitySelected(int criteriaScaleNumber);
}
