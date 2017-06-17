package homepunk.alternativeresolutions.presentation.presenter.intefaces;

import homepunk.alternativeresolutions.presentation.view.DataInputView;
import homepunk.alternativeresolutions.presentation.models.Criterion;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public interface DataInputPresenter extends Presenter<DataInputView> {
    void createCriteriaInput();

    void onAddCriterionValuationClick(Criterion criterion);

    void onCriterionValuationClick(Criterion criterion, int position);

    void onCriterionValuationLongClick(Criterion criterion, int position);

    void onFindAlternatesButtonClick();
}
