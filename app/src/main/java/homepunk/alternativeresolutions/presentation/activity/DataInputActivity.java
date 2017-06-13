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
import butterknife.OnClick;
import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.base.BaseApp;
import homepunk.alternativeresolutions.presentation.custom.CriterionLayout;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DataInputPresenter;
import homepunk.alternativeresolutions.presentation.view.DataInputView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;
import timber.log.Timber;

public class DataInputActivity extends AppCompatActivity implements DataInputView {
    @Inject
    LayoutInflater inflater;
    @Inject
    DataInputPresenter presenter;

    @BindView(R.id.activity_criteria_criteria_container)
    LinearLayout criteriaLayout;
    @BindView(R.id.criteria_quantity_spinner)
    Spinner criteriaQuantitySpinner;

    private HashMap<Criterion, CriterionLayout> criteriaCriteriaLayoutHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

        init();
        initUI();
    }

    @Override
    public void addCriterion(Criterion criterion) {
        final CriterionLayout criterionLayout = new CriterionLayout(this);
        criterionLayout.setCriterion(criterion);
        criterionLayout.setOnValuationClickListener(position -> presenter.onCriterionValuationClick(criterion, position));
        criterionLayout.setOnAddValuationClickListener(v -> presenter.onAddCriterionValuationClick(criterion));

        criteriaCriteriaLayoutHashMap.put(criterion, criterionLayout);
        criterionLayout.addView(criterionLayout);
        Timber.i("Size of criterion layouts list after add: " + String.valueOf(criteriaCriteriaLayoutHashMap.size()));
    }

    @Override
    public void removeCriterion(Criterion criterionToRemove) {
        CriterionLayout criterionLayout = criteriaCriteriaLayoutHashMap.get(criterionToRemove);
        Iterator iterator = criteriaCriteriaLayoutHashMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Criterion criterion = (Criterion) pair.getKey();

            if (criterion.equals(criterionToRemove)) {
                iterator.remove();
            }
        }

        criterionLayout.removeView(criterionLayout);
        Timber.i("Size of criteria layouts list after remove: " + String.valueOf(criteriaCriteriaLayoutHashMap.size()));
    }

    @Override
    public void addCriterionValuation(Criterion criterion) {
        CriterionLayout criterionLayout = criteriaCriteriaLayoutHashMap.get(criterion);

        criterionLayout.setCriterion(criterion);
    }

    @Override
    public void removeCriterionValuation(Criterion criterion, Valuation valuation) {
        CriterionLayout criterionLayout = criteriaCriteriaLayoutHashMap.get(criterion);

        criterionLayout.removeValuation(valuation);

    }

    @OnClick(R.id.activity_criteria_calculate_button)
    public void onCalculateViewClick() {
        presenter.onBuildDominationGraphButtonClick();
    }

    private void init() {
        ButterKnife.bind(this);
        BaseApp.getBaseComponent().inject(this);
        presenter.init(this);
        criteriaCriteriaLayoutHashMap = new HashMap<>();
    }

    private void initUI() {
        setUpCriteriaQuantitySpinner();
    }

    private void setUpCriteriaQuantitySpinner() {
        final List<Integer> criteriaQuantity = new ArrayList<>(3);

        for (int i = 2; i < 5; i++) {
            criteriaQuantity.add(i);
        }

        final ArrayAdapter criteriaNumbersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, criteriaQuantity);

        criteriaQuantitySpinner.setAdapter(criteriaNumbersAdapter);
        criteriaQuantitySpinner.setSelection(0);
        criteriaQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int criteriaNumber = criteriaQuantity.get(position);

                presenter.onCriterionQuantityEntered(criteriaNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
