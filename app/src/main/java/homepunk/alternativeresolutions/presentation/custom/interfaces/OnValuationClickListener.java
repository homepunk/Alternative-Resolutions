package homepunk.alternativeresolutions.presentation.custom.interfaces;

import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;

/**
 * Created by homepunk on 6/13/17.
 */

public interface OnValuationClickListener {
    void onCriterionValuationClick(Criterion criterion, int position);

    void onCriterionValuationLongLick(Criterion criterion, int position);
}
