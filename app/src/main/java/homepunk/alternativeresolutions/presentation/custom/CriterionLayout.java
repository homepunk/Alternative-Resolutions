package homepunk.alternativeresolutions.presentation.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.custom.adapter.ValuationAdapter;
import homepunk.alternativeresolutions.presentation.custom.interfaces.OnValuationClickListener;
import homepunk.alternativeresolutions.presentation.custom.interfaces.SimpleClickListener;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;

/**
 * Created by Homepunk on 12.06.2017.
 **/

public class CriterionLayout extends LinearLayout {
    @BindView(R.id.item_criteria_valuations_recycle_view)
    protected RecyclerView valuationsRecycler;
    @BindView(R.id.item_criteria_name)
    protected TextView criterionNameView;
    @BindView(R.id.item_criteria_add_button)
    protected FrameLayout addValuationView;

    private Criterion criterion;
    private ValuationAdapter valuationAdapter;
    private OnValuationClickListener valuationClickListener;
    private OnClickListener addValuationListener;

    public CriterionLayout(Context context) {
        super(context);
        init(context);
    }

    public CriterionLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_criterion, this);
        ButterKnife.bind(this, root);
        initCriteriaValuationsRecycler(context);
    }

    private void initCriteriaValuationsRecycler(Context context) {
        valuationAdapter = new ValuationAdapter(context);
        valuationAdapter.setOnValuationClickListener(new SimpleClickListener() {
            @Override
            public void onClick(int position) {
                if (valuationClickListener != null) {
                    valuationClickListener.onCriterionValuationClick(criterion, position);
                }
            }

            @Override
            public void onLongCLick(int position) {
                if (valuationClickListener != null) {
                    valuationClickListener.onCriterionValuationLongLick(criterion, position);
                }
            }
        });

        valuationsRecycler.setAdapter(valuationAdapter);
        valuationsRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
        update();
    }

    public void addValuation(Valuation valuation) {
        valuationAdapter.update(valuation);
    }

    public void removeValuation(Valuation valuationToRemove) {
        List<Valuation> valuations = criterion.getValuations();
        Iterator<Valuation> iterator = valuations.iterator();

        while (iterator.hasNext()) {
            Valuation valuation = iterator.next();

            if (valuation.getLineIndex() == valuationToRemove.getLineIndex()) {
                iterator.remove();
            }
        }

        valuationAdapter.update(valuations);
    }

    public void updateCriteriaValuations(Criterion criterion) {
        valuationAdapter.update(criterion.getValuations());
    }

    private void update() {
        valuationAdapter.update(criterion.getValuations());
        criterionNameView.setText(criterion.getFullName());
    }

    public void setOnValuationClickListener(OnValuationClickListener valuationClickListener) {
        this.valuationClickListener = valuationClickListener;
    }

    public void setOnAddValuationClickListener(OnClickListener addValuationListener) {
        addValuationView.setOnClickListener(addValuationListener);
    }
}
