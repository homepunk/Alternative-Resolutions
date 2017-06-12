package homepunk.alternativeresolutions.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.base.BaseApp;
import homepunk.alternativeresolutions.presentation.custom.CriteriaLayout;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.CriteriasPresenter;
import homepunk.alternativeresolutions.presentation.view.CriteriasView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.CriteriaValuation;

public class CriteriasActivity extends AppCompatActivity implements CriteriasView {
    @Inject
    LayoutInflater inflater;
    @Inject
    CriteriasPresenter presenter;

    @BindView(R.id.criterias_linear_layout)
    LinearLayout criteriasLayout;
    @BindView(R.id.activity_criterias_criterias_container)
    LinearLayout criteriasContainer;
    @BindView(R.id.criterias_quantity_spinner)
    Spinner criteriasQuantitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterias);

        init();
        initUI();
    }

    @Override
    public void onCriteriasEntered(List<CriteriaValuation> criteriaValuations) {

    }

    @Override
    public void onCriteriaNumberEntered(int criteriaScalesNumber) {
        for (int i = 0; i < criteriaScalesNumber; i++) {

        }
    }

    @Override
    public void onCriteriasEnterFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeCriteria(int index) {

    }

    @Override
    public void addCriteria(Criteria criteria) {
        View criteriaContainer = inflater.inflate(R.layout.item_criteria, null, false);
        CriteriaLayout criteriaLayout = new CriteriaLayout(this);
        criteriaLayout.setCriteria(criteria);

        criteriasLayout.addView(criteriaLayout);


        View valuationsContainer = ButterKnife.findById(this, R.id.item_criteria_valuations_container);

//
//        addCriteriaValuation(valuationsContainer, "k11");
//        addCriteriaValuation(valuationsContainer, "k12");
//        addCriteriaValuation(valuationsContainer, "k13");
//        addCriteriaValuation(valuationsContainer, "k14");


    }

    public void addCriteriaValuation(View parentContainer, String index) {
        LinearLayout valuationItem = (LinearLayout) inflater.inflate(R.layout.item_criteria_valuation, null, false);
        TextView valuationName = (TextView) ButterKnife.bind(R.id.item_criteria_valuation_index, valuationItem);

        valuationName.setText(index);

        ((LinearLayout) parentContainer).addView(valuationItem);
    }

    private void init() {
        ButterKnife.bind(this);
        BaseApp.getBaseComponent().inject(this);
        presenter.init(this);
    }

    private void initUI() {
        setUpCriteriasQuantitySpinner();

        final List<Integer> criteriaValuations = new ArrayList<>(8);

        for (int i = 2; i < 10; i++) {
            criteriaValuations.add(i);
        }

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

                presenter.onCriteriaQuantitySelected(criteriasNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
