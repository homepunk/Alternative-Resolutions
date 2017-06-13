package homepunk.alternativeresolutions.presentation.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import homepunk.alternativeresolutions.presentation.base.BasePresenter;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DataInputPresenter;
import homepunk.alternativeresolutions.presentation.view.DataInputView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;
import timber.log.Timber;

import static homepunk.alternativeresolutions.presentation.data.Constants.DEFAULT_VALUATIONS;
import static homepunk.alternativeresolutions.presentation.data.Constants.MAX_VALUATIONS_SIZE;
import static homepunk.alternativeresolutions.presentation.data.Constants.MIN_VALUATIONS_SIZE;
import static homepunk.alternativeresolutions.presentation.data.Constants.VALUATIONS_RATINGS;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class DataInputPresenterImpl extends BasePresenter<DataInputView> implements DataInputPresenter {
    private List<Criterion> criteria;

    public DataInputPresenterImpl() {
        criteria = new ArrayList<>(4);
    }

    @Override
    public void onCriterionQuantityEntered(int quantity) {
        if (quantity > criteria.size()) {
            int difference = quantity - criteria.size();

            for (int i = 0; i < difference; i++) {
                createCriterion();
            }
        }

        if (quantity < criteria.size()) {
            int difference = criteria.size() - quantity;

            for (int i = criteria.size() - 1; i >= difference; i--) {

                Criterion criterion = criteria.get(i);

                if (criterion.getIndex() > quantity) {
                    removeCriterion(criterion);
                }
            }
        }
    }

    @Override
    public void onAddCriterionValuationClick(Criterion criterion) {
        List<Valuation> criterionValuations = criterion.getValuations();
        List<Integer> valuationsRatings = new ArrayList<>();

        for (int i = 0; i < criterionValuations.size(); i++) {
            Valuation valuation = criterionValuations.get(i);
            valuationsRatings.add(valuation.getValuationRating());
        }

        if (valuationsRatings.size() < MAX_VALUATIONS_SIZE) {
            int lineIndex = valuationsRatings.size() + 1;

            for (int valuationRating : VALUATIONS_RATINGS) {
                if (!valuationsRatings.contains(valuationRating)) {
                    Valuation valuation = new Valuation();

                    valuation.setColumnIndex(criterion.getIndex());
                    valuation.setLineIndex(lineIndex);
                    valuation.setValuationRating(valuationRating);

                    addCriterionValuation(criterion, valuation);
                    break;
                }
            }
        }
    }

    @Override
    public void onCriterionValuationClick(Criterion criterion, int position) {
        List<Valuation> valuations = criterion.getValuations();
        Valuation valuationToRemove = valuations.get(position);

        if (valuations.size() > MIN_VALUATIONS_SIZE) {
            view.removeCriterionValuation(criterion, valuationToRemove);
            removeCriterionValuationFromList(criterion, valuationToRemove);
        }

    }

    @Override
    public void onBuildDominationGraphButtonClick() {
        for (Criterion criterion : criteria) {
            Timber.i("Criterion: " + criterion.getFullName());
            for (Valuation valuation : criterion.getValuations()) {
                Timber.i("Valuation: " + valuation.getPrefix() + valuation.getColumnIndex() + valuation.getLineIndex());
            }
        }
    }

    private void removeCriterionValuationFromList(Criterion criterion, Valuation valuation) {
        Criterion criterionToUpdate = criteria.get(criterion.getArrayIndex());

        criterionToUpdate.removeValuation(valuation);
        recountValuationsIndexes(criterionToUpdate.getValuations());
    }

    private void addCriterionValuation(Criterion criterion, Valuation valuation) {
        Criterion criterionToUpdate = criteria.get(criterion.getArrayIndex());

        criterionToUpdate.addValuation(valuation);
        criterionToUpdate.sortValuations();
        recountValuationsIndexes(criterionToUpdate.getValuations());

        Criterion updatedCriterion = criteria.get(criterion.getArrayIndex());

        view.addCriterionValuation(updatedCriterion);
    }

    private void recountValuationsIndexes(List<Valuation> valuations) {
        for (int i = 0; i < valuations.size(); i++) {
            int lineIndex = i + 1;

            valuations.get(i).setLineIndex(lineIndex);
        }
    }

    private void createCriterion() {
        Criterion criterion = new Criterion();
        int index = generateIndex();

        for (int i = 0; i < 4; i++) {
            Valuation valuation = new Valuation();

            valuation.setValuationRating(DEFAULT_VALUATIONS[i]);
            valuation.setColumnIndex(index);
            valuation.setLineIndex(i + 1);
            criterion.addValuation(valuation);
        }

        criterion.setIndex(index);

        criteria.add(criterion);
        view.addCriterion(criterion);
    }

    /**
     * @return New shifted by one index for created criterion
     * */

    private int generateIndex() {
        return criteria.size() + 1;
    }

    private void removeCriterion(Criterion criterion) {
        removeCriterionFromList(criterion);
        view.removeCriterion(criterion);
    }

    private void removeCriterionFromList(Criterion criterionToRemove) {
        Iterator<Criterion> iterator = criteria.iterator();

        while (iterator.hasNext()) {
            Criterion criterion = iterator.next();

            if (criterion.getIndex() == criterionToRemove.getIndex()) {
                iterator.remove();
            }
        }
    }
}
