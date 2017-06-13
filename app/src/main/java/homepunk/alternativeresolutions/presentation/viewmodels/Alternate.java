package homepunk.alternativeresolutions.presentation.viewmodels;

import android.support.v4.util.Pair;

/**
 * Created by homepunk on 6/14/17.
 */

public class Alternate {
    private Pair<Valuation, Valuation> alternate;

    public Pair<Valuation, Valuation> getAlternate() {
        return alternate;
    }

    public void setAlternate(Pair<Valuation, Valuation> alternate) {
        this.alternate = alternate;
    }
}
