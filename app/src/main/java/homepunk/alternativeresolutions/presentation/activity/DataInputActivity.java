package homepunk.alternativeresolutions.presentation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.base.BaseApp;
import homepunk.alternativeresolutions.presentation.custom.CriterionLayout;
import homepunk.alternativeresolutions.presentation.custom.interfaces.OnValuationClickListener;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DataInputPresenter;
import homepunk.alternativeresolutions.presentation.view.DataInputView;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;

public class DataInputActivity extends AppCompatActivity implements DataInputView {
    @Inject
    LayoutInflater inflater;
    @Inject
    DataInputPresenter presenter;

    @BindView(R.id.activity_criteria_criteria_container)
    LinearLayout criteriaLayout;

    private HashMap<Criterion, CriterionLayout> criteriaCriteriaLayoutHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.createCriteriaInput();
    }

    @Override
    public void addCriterion(Criterion criterion) {
        final CriterionLayout criterionLayout = new CriterionLayout(this);
        criterionLayout.setCriterion(criterion);
        criterionLayout.setOnValuationClickListener(new OnValuationClickListener() {
            @Override
            public void onCriterionValuationClick(Criterion criterion, int position) {
                presenter.onCriterionValuationClick(criterion, position);
            }

            @Override
            public void onCriterionValuationLongLick(Criterion criterion, int position) {
                presenter.onCriterionValuationLongClick(criterion, position);

            }

        });
        criterionLayout.setOnAddValuationClickListener(v -> presenter.onAddCriterionValuationClick(criterion));

        criteriaCriteriaLayoutHashMap.put(criterion, criterionLayout);
        criteriaLayout.addView(criterionLayout);
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

    @Override
    public void onAlternateSelectionFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.activity_criteria_find_alternates_button)
    public void onFindAlternatesButtonClick() {
        presenter.onFindAlternatesButtonClick();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public DataInputActivity getActivity() {
        return this;
    }

    private void init() {
        ButterKnife.bind(this);
        BaseApp.getBaseComponent().inject(this);
        presenter.init(this);
        criteriaCriteriaLayoutHashMap = new HashMap<>();
    }
}
