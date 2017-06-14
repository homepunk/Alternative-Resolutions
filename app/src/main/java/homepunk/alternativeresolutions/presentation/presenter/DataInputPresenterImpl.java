package homepunk.alternativeresolutions.presentation.presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.base.BasePresenter;
import homepunk.alternativeresolutions.presentation.fragment.DominationGraphFragment;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DataInputPresenter;
import homepunk.alternativeresolutions.presentation.view.DataInputView;
import homepunk.alternativeresolutions.presentation.viewmodels.Alternate;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;

import static homepunk.alternativeresolutions.presentation.data.Constants.ALTERNATE_ERROR_MESSAGE;
import static homepunk.alternativeresolutions.presentation.data.Constants.DEFAULT_VALUATIONS;
import static homepunk.alternativeresolutions.presentation.data.Constants.DOMINATION_GRAPH_FRAGMENT_KEY;
import static homepunk.alternativeresolutions.presentation.data.Constants.KEY_SELECTED_ALTERNATE;
import static homepunk.alternativeresolutions.presentation.data.Constants.MAX_CRITERIA_SIZE;
import static homepunk.alternativeresolutions.presentation.data.Constants.MAX_VALUATIONS_SIZE;
import static homepunk.alternativeresolutions.presentation.data.Constants.MIN_VALUATIONS_SIZE;
import static homepunk.alternativeresolutions.presentation.data.Constants.VALUATIONS_RATINGS;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class DataInputPresenterImpl extends BasePresenter<DataInputView> implements DataInputPresenter {
    private List<Criterion> criteria;
    private Alternate alternate;
    private boolean isFragmentCreated;

    @Override
    public void createCriteriaInput() {
        if (criteria == null) {
            criteria = new ArrayList<>(MAX_CRITERIA_SIZE);
        }

        createCriterion();
        createCriterion();
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
        if (alternate == null || !alternate.isEmpty()) {
            alternate = new Alternate();
        }

        int pairPosition = criterion.getIndex();
        Valuation valuation = criterion.getValuations().get(position);
        switch (pairPosition) {
            case 1: {
                alternate.setFirstValuation(valuation);
                view.showAlternateValuation(valuation);
                break;
            }

            case 2: {
                alternate.setSecondValuation(valuation);
                view.showAlternateValuation(valuation);
                break;
            }
        }
    }

    @Override
    public void onCriterionValuationLongClick(Criterion criterion, int position) {
        List<Valuation> valuations = criterion.getValuations();
        Valuation valuationToRemove = valuations.get(position);

        if (valuations.size() > MIN_VALUATIONS_SIZE) {
            view.removeCriterionValuation(criterion, valuationToRemove);
            removeCriterionValuationFromList(criterion, valuationToRemove);
        }

    }

    @Override
    public void onFindAlternatesButtonClick() {
        if (view != null) {
            if (isAlternateSelected()) {
                FragmentManager fragmentManager = view.getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                DominationGraphFragment fragment = createDominationGraphFragmentWithCriteriaArgument();

                if (isFragmentCreated) {
                    transaction.replace(R.id.activity_criteria_fragment_domination_graph_container, fragment);
                    transaction.commit();
                } else {
                    transaction.add(R.id.activity_criteria_fragment_domination_graph_container, fragment);
                    transaction.commit();
                }
            } else {
                view.onAlternateSelectionFailed(ALTERNATE_ERROR_MESSAGE);
            }

        }
    }

    private DominationGraphFragment createDominationGraphFragmentWithCriteriaArgument() {
        isFragmentCreated = true;
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_SELECTED_ALTERNATE, alternate);
        bundle.putSerializable(DOMINATION_GRAPH_FRAGMENT_KEY, (Serializable) criteria);

        DominationGraphFragment fragment = new DominationGraphFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private boolean isAlternateSelected() {
        return alternate != null && !alternate.isEmpty() && alternate.getAlternate().first != null && alternate.getAlternate().second != null;
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
     */

    private int generateIndex() {
        return criteria.size() + 1;
    }
}
