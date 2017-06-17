package homepunk.alternativeresolutions.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import homepunk.alternativeresolutions.presentation.base.BasePresenter;
import homepunk.alternativeresolutions.presentation.models.Alternate;
import homepunk.alternativeresolutions.presentation.models.Valuation;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DominationGraphPresenter;
import homepunk.alternativeresolutions.presentation.view.DominationGraphView;
import homepunk.alternativeresolutions.presentation.models.Criterion;
import homepunk.alternativeresolutions.presentation.models.DominationGraph;
import timber.log.Timber;

import static homepunk.alternativeresolutions.presentation.data.Constants.CRITERIA_SIZE;
import static timber.log.Timber.i;

/**
 * Created by homepunk on 6/14/17.
 */

public class DominationGraphPresenterImpl extends BasePresenter<DominationGraphView> implements DominationGraphPresenter {
    private DominationGraph dominationGraph;
    private Alternate selectedAlternate;

    private int columnsCount, linesCount;
    private List<Alternate> incorompableAlternates;
    private List<Alternate> betterAlternates;
    private List<Alternate> worseAlternates;
    private Alternate bestAlternate;
    private Alternate worstAlternate;


    @Override
    public void designDominationGraph(List<Criterion> criteria) {
        int criterionQuantity = criteria.size();

        switch (criterionQuantity) {
            case CRITERIA_SIZE: {
                dominationGraph = new DominationGraph(criteria);
                dominationGraph.buildDominationGraph();

                view.buildDominationGraph(dominationGraph);
            }
        }

        linesCount = dominationGraph.getLinesCount();
        Timber.i("Lines count: " + linesCount);
        columnsCount = dominationGraph.getColumnsCount();
        Timber.i("Columns count: " + columnsCount);
    }

    @Override
    public void onDominationGraphBuilt() {
        compareAlternates(dominationGraph.getAlternates());

        view.showBestAlternate(bestAlternate);
        view.showWorstAlternate(worstAlternate);
        view.showWorseAlternates(worseAlternates);
        view.showBetterAlternates(betterAlternates);
        view.showIncomparableAlternates(incorompableAlternates);
    }

    @Override
    public void setSelectedAlternate(Alternate alternate) {
        selectedAlternate = alternate;
    }

    public void compareAlternates(List<Alternate> alternates) {
        worseAlternates = new ArrayList<>();
        betterAlternates = new ArrayList<>();
        incorompableAlternates = new ArrayList<>();

        for (Alternate alternate : alternates) {
            if (!isSelectedAlternate(alternate)) {
                if (alternate.getAlternate().first.getLineIndex() == dominationGraph.getColumnsCount()
                        && alternate.getAlternate().second.getLineIndex() == dominationGraph.getLinesCount()) {
                    worstAlternate = alternate;
                }

                if (alternate.getAlternate().first.getLineIndex() == 1
                        && alternate.getAlternate().second.getLineIndex() == 1) {
                    bestAlternate = alternate;
                }

                if (isAlternateBetterSelected(alternate)) {
                    betterAlternates.add(alternate);
                } else if (isAlternateWorseSelected(alternate)) {
                    worseAlternates.add(alternate);
                } else {
                    incorompableAlternates.add(alternate);
                }
            }
        }

        for (Alternate alternate : betterAlternates) {
            i("Better alternates: " + alternate.toString());
        }

        for (Alternate worstAlternate : worseAlternates) {
            i("Worse alternates: " + worstAlternate.toString());
        }

        for (Alternate incorompableAlternate : incorompableAlternates) {
            i("Incomparable alternates: " + incorompableAlternate.toString());
        }
    }

    private boolean isSelectedAlternate(Alternate alternate) {
        return alternate.getAlternate().first.getLineIndex() == selectedAlternate.getAlternate().first.getLineIndex()
                && alternate.getAlternate().second.getLineIndex() == selectedAlternate.getAlternate().second.getLineIndex();
    }

    private boolean isAlternateWorseSelected(Alternate alternate) {
        Valuation first = alternate.getAlternate().first;
        Valuation second = alternate.getAlternate().second;
        Valuation selectedFirst = selectedAlternate.getAlternate().first;
        Valuation selectedSecond = selectedAlternate.getAlternate().second;

        if (first.getLineIndex() >= selectedFirst.getLineIndex()
                && second.getLineIndex() >= selectedSecond.getLineIndex()) {
            return true;
        }

        return false;
    }

    private boolean isAlternateBetterSelected(Alternate alternate) {
        Valuation first = alternate.getAlternate().first;
        Valuation second = alternate.getAlternate().second;
        Valuation selectedFirst = selectedAlternate.getAlternate().first;
        Valuation selectedSecond = selectedAlternate.getAlternate().second;

        if (first.getLineIndex() <= selectedFirst.getLineIndex()
                && second.getLineIndex() <= selectedSecond.getLineIndex()) {
            return true;
        }

        return false;
    }

}
