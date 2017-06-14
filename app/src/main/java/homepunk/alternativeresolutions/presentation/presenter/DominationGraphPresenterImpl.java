package homepunk.alternativeresolutions.presentation.presenter;

import java.util.List;

import homepunk.alternativeresolutions.presentation.base.BasePresenter;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DominationGraphPresenter;
import homepunk.alternativeresolutions.presentation.view.DominationGraphView;
import homepunk.alternativeresolutions.presentation.viewmodels.Alternate;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.DominationGraph;
import timber.log.Timber;

import static homepunk.alternativeresolutions.presentation.data.Constants.MIN_CRITERIA_SIZE;

/**
 * Created by homepunk on 6/14/17.
 */

public class DominationGraphPresenterImpl extends BasePresenter<DominationGraphView> implements DominationGraphPresenter {
    private List<Alternate> alternates;
    private DominationGraph dominationGraph;

    @Override
    public void designDominationGraph(List<Criterion> criteria) {
        int criterionQuantity = criteria.size();

        switch (criterionQuantity) {
            case MIN_CRITERIA_SIZE: {
                dominationGraph = new DominationGraph(criteria);
                dominationGraph.buildDominationGraph();

                view.buildDominationGraph(dominationGraph);

                for (int i = 0; i < dominationGraph.getColumnsCount(); i++) {
                    for (int j = 0; j < dominationGraph.getLinesCount(); j++) {
                        Timber.i("Alternate: " + dominationGraph.getAlternatesMatrix()[i][j].getAlternate().first.toString() + " - "
                                +  dominationGraph.getAlternatesMatrix()[i][j].getAlternate().second.toString());
                    }
                }
            }
        }
    }

    @Override
    public void onDominationGraphBuilt() {

    }
}
