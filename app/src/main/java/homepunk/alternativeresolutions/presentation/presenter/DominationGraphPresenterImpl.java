package homepunk.alternativeresolutions.presentation.presenter;

import java.util.List;

import homepunk.alternativeresolutions.presentation.base.BasePresenter;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DominationGraphPresenter;
import homepunk.alternativeresolutions.presentation.view.DominationGraphView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.DominationGraph;

/**
 * Created by homepunk on 6/14/17.
 */

public class DominationGraphPresenterImpl extends BasePresenter<DominationGraphView> implements DominationGraphPresenter {
    private DominationGraph dominationGraph;

    @Override
    public void designDominationGraph(List<Criterion> criteria) {

    }

    @Override
    public void onDominationGraphBuilt() {

    }



}
