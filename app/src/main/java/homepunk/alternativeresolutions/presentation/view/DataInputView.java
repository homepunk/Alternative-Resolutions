package homepunk.alternativeresolutions.presentation.view;

import homepunk.alternativeresolutions.presentation.activity.DataInputActivity;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public interface DataInputView extends View {
    DataInputActivity getActivity();

    void addCriterion(Criterion criterion);

    void addCriterionValuation(Criterion criterion);

    void removeCriterionValuation(Criterion criterion, Valuation valuation);

    void onAlternateSelectionFailed(String message);
}
