package homepunk.alternativeresolutions.presentation.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import homepunk.alternativeresolutions.presentation.base.BasePresenter;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.CriteriasPresenter;
import homepunk.alternativeresolutions.presentation.view.CriteriasView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;

import static homepunk.alternativeresolutions.presentation.data.Constants.DEFAULT_VALUATIONS;
import static homepunk.alternativeresolutions.presentation.data.Constants.MAX_VALUATIONS_SIZE;
import static homepunk.alternativeresolutions.presentation.data.Constants.MIN_VALUATIONS_SIZE;
import static homepunk.alternativeresolutions.presentation.data.Constants.VALUATIONS;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public class CriteriasPresenterImpl extends BasePresenter<CriteriasView> implements CriteriasPresenter {
    private List<Criteria> criterias;

    public CriteriasPresenterImpl() {
        criterias = new ArrayList<>(4);
    }

    @Override
    public void onCriteriaNumberSelected(int number) {
        if (number > criterias.size()) {
            int difference = number - criterias.size();

            for (int i = 0; i < difference; i++) {
                createCriteria();
            }
        }

        if (number < criterias.size()) {
            int difference = criterias.size() - number;

            for (int i = criterias.size() - 1; i >= difference; i--) {

                Criteria criteria = criterias.get(i);

                if (criteria.getIndex() > number) {
                    removeCriteria(criteria);
                }
            }
        }
    }

    @Override
    public void onAddCriteriaValuationClick(Criteria criteria) {
        List<Valuation> criteriaValuations = criteria.getValuations();
        List<Integer> valuations = new ArrayList<>();

        for (int i = 0; i < criteriaValuations.size(); i++) {
            Valuation valuation = criteriaValuations.get(i);
            valuations.add(valuation.getValuation());
        }

        if (valuations.size() < MAX_VALUATIONS_SIZE) {
            int lineIndex = valuations.size() + 1;

            for (int valuation : VALUATIONS) {
                if (!valuations.contains(valuation)) {
                    Valuation newValuation = new Valuation();

                    newValuation.setColumnIndex(criteria.getIndex());
                    newValuation.setLineIndex(lineIndex);
                    newValuation.setValuation(valuation);

                    addCriteriaValuationToList(criteria, newValuation);

                    view.addCriteriaValuation(criteria, newValuation);
                    break;
                }
            }
        }
    }

    @Override
    public void onCriteriaValuationClick(Criteria criteria, int position) {
        List<Valuation> valuations = criteria.getValuations();
        Valuation valuationToRemove = valuations.get(position);

        if (valuations.size() > MIN_VALUATIONS_SIZE) {
            view.removeCriteriaValuation(criteria, valuationToRemove);
            removeCriteriaValuationFromList(criteria, valuationToRemove);
        }

    }

    private void removeCriteriaValuationFromList(Criteria criteria, Valuation valuation) {
        Criteria criteriaToUpdate = criterias.get(criteria.getArrayIndex());

        criteriaToUpdate.removeCriteriaValuation(valuation);
        recountValuationsIndexes(criteriaToUpdate.getValuations());
    }

    private void addCriteriaValuationToList(Criteria criteria, Valuation valuation) {
        Criteria criteriaToUpdate = criterias.get(criteria.getArrayIndex());

        criteriaToUpdate.addValuation(valuation);
        criteriaToUpdate.sortValuations();
        recountValuationsIndexes(criteriaToUpdate.getValuations());
    }

    private void recountValuationsIndexes(List<Valuation> valuations) {
        for (int i = 0; i < valuations.size(); i++) {
            int lineIndex = i + 1;

            valuations.get(i).setLineIndex(lineIndex);
        }
    }

    private void createCriteria() {
        Criteria criteria = new Criteria();
        int index = generateIndex();

        for (int i = 0; i < 4; i++) {
            Valuation valuation = new Valuation();

            valuation.setValuation(DEFAULT_VALUATIONS[i]);
            valuation.setColumnIndex(index);
            valuation.setLineIndex(i + 1);
            criteria.addValuation(valuation);
        }

        criteria.setIndex(index);

        criterias.add(criteria);
        view.addCriteria(criteria);
    }

    private int generateIndex() {
        return criterias.size() + 1;
    }

    private void removeCriteria(Criteria criteria) {
        removeCriteriaFromList(criteria);
        view.removeCriteria(criteria);
    }

    private void removeCriteriaFromList(Criteria criteriaToRemove) {
        Iterator<Criteria> iterator = criterias.iterator();

        while (iterator.hasNext()) {
            Criteria criteria = iterator.next();

            if (criteria.getIndex() == criteriaToRemove.getIndex()) {
                iterator.remove();
            }
        }
    }
}
