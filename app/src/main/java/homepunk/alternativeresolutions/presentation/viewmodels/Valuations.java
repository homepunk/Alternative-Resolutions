package homepunk.alternativeresolutions.presentation.viewmodels;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_BAD;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_BETTER;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_GOOD;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_NORMAL;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_THE_BEST;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_THE_WORST;
import static homepunk.alternativeresolutions.presentation.viewmodels.Valuations.VALUATION_WORSE;

/**
 * Created by Homepunk on 12.06.2017.
 **/
@IntDef({
        VALUATION_THE_WORST,
        VALUATION_WORSE,
        VALUATION_BAD,
        VALUATION_NORMAL,
        VALUATION_GOOD,
        VALUATION_BETTER,
        VALUATION_THE_BEST
})

@Retention(RetentionPolicy.SOURCE)
public @interface Valuations {
    int VALUATION_THE_WORST = 0;
    int VALUATION_WORSE = 1;
    int VALUATION_BAD = 2;
    int VALUATION_NORMAL = 3;
    int VALUATION_GOOD = 4;
    int VALUATION_BETTER = 5;
    int VALUATION_THE_BEST = 6;
}