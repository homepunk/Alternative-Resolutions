package homepunk.alternativeresolutions.presentation.presenter.intefaces;

import java.util.List;

import homepunk.alternativeresolutions.presentation.view.DominationGraphView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;

/**
 * Created by homepunk on 6/14/17.
 */

public interface DominationGraphPresenter extends Presenter<DominationGraphView> {
    void designDominationGraph(List<Criterion> criteria);

    void onDominationGraphBuilt();

}
