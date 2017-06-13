package homepunk.alternativeresolutions.presentation.presenter.intefaces;

import homepunk.alternativeresolutions.presentation.view.DataInputView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public interface DataInputPresenter extends Presenter<DataInputView> {
    void onCriterionQuantityEntered(int quantity);

    void onAddCriterionValuationClick(Criterion criterion);

    void onCriterionValuationClick(Criterion criterion, int position);

    void onBuildDominationGraphButtonClick();
}
