package homepunk.alternativeresolutions.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.base.BaseApp;
import homepunk.alternativeresolutions.presentation.custom.CriteriaLayout;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.CriteriasPresenter;
import homepunk.alternativeresolutions.presentation.view.CriteriasView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;
import timber.log.Timber;

public class CriteriasActivity extends AppCompatActivity implements CriteriasView {
    @Inject
    LayoutInflater inflater;
    @Inject
    CriteriasPresenter presenter;

    @BindView(R.id.criterias_linear_layout)
    LinearLayout criteriasLayout;
    @BindView(R.id.criterias_quantity_spinner)
    Spinner criteriasQuantitySpinner;

    private HashMap<Criteria, CriteriaLayout> criteriaCriteriaLayoutHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterias);

        init();
        initUI();
    }

    @Override
    public void addCriteria(Criteria criteria) {
        final CriteriaLayout criteriaLayout = new CriteriaLayout(this);
        criteriaLayout.setCriteria(criteria);
        criteriaLayout.setOnValuationClickListener(position -> presenter.onCriteriaValuationClick(criteria, position));
        criteriaLayout.setOnAddValuationClickListener(v -> presenter.onAddCriteriaValuationClick(criteria));

        criteriaCriteriaLayoutHashMap.put(criteria, criteriaLayout);
        criteriasLayout.addView(criteriaLayout);
        Timber.i("Size of criteria layouts list after add: " + String.valueOf(criteriaCriteriaLayoutHashMap.size()));
    }

    @Override
    public void removeCriteria(Criteria criteriaToRemove) {
        CriteriaLayout criteriaLayout = criteriaCriteriaLayoutHashMap.get(criteriaToRemove);
        Iterator iterator = criteriaCriteriaLayoutHashMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Criteria criteria = (Criteria) pair.getKey();

            if (criteria.equals(criteriaToRemove)) {
                iterator.remove();
            }
        }

        criteriasLayout.removeView(criteriaLayout);
        Timber.i("Size of criteria layouts list after remove: " + String.valueOf(criteriaCriteriaLayoutHashMap.size()));
    }

    @Override
    public void addCriteriaValuation(Criteria criteria, Valuation valuation) {
        CriteriaLayout criteriaLayout = criteriaCriteriaLayoutHashMap.get(criteria);

        criteriaLayout.addValuation(valuation);
    }

    @Override
    public void removeCriteriaValuation(Criteria criteria, Valuation valuation) {
        CriteriaLayout criteriaLayout = criteriaCriteriaLayoutHashMap.get(criteria);

        criteriaLayout.removeValuation(valuation);

    }

    private void init() {
        ButterKnife.bind(this);
        BaseApp.getBaseComponent().inject(this);
        presenter.init(this);
        criteriaCriteriaLayoutHashMap = new HashMap<>();
    }

    private void initUI() {
        setUpCriteriasQuantitySpinner();
    }

    private void setUpCriteriasQuantitySpinner() {
        final List<Integer> criteriaQuantity = new ArrayList<>(3);

        for (int i = 2; i < 5; i++) {
            criteriaQuantity.add(i);
        }

        final ArrayAdapter criteriaNumbersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, criteriaQuantity);

        criteriasQuantitySpinner.setAdapter(criteriaNumbersAdapter);
        criteriasQuantitySpinner.setSelection(0);
        criteriasQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int criteriasNumber = criteriaQuantity.get(position);

                presenter.onCriteriaNumberSelected(criteriasNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
