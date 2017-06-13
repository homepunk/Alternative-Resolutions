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
import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;

/**
 * Created by Homepunk on 12.06.2017.
 **/

public class CriteriaLayout extends LinearLayout {
    @BindView(R.id.item_criteria_valuations_recycle_view)
    protected RecyclerView valuationsRecycler;
    @BindView(R.id.item_criteria_name)
    protected TextView criteriaNameView;
    @BindView(R.id.item_criteria_add_button)
    protected FrameLayout addCriteriaView;

    private Criteria criteria;
    private ValuationAdapter valuationAdapter;
    private OnValuationClickListener valuationClickListener;
    private OnClickListener addValuationListener;

    public CriteriaLayout(Context context) {
        super(context);
        init(context);
    }

    public CriteriaLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_criteria, this);
        ButterKnife.bind(this, root);
        initCriteriaValuationsRecycler(context);
    }

    private void initCriteriaValuationsRecycler(Context context) {
        valuationAdapter = new ValuationAdapter(context);
        valuationAdapter.setOnValuationClickListener(position -> {
            if (valuationClickListener != null) {
                valuationClickListener.onCriteriaValuationClick(position);
            }
        });

        valuationsRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        valuationsRecycler.setAdapter(valuationAdapter);

    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
        update();
    }

    public void addValuation(Valuation valuation) {
        valuationAdapter.update(valuation);
    }

    public void removeValuation(Valuation valuationToRemove) {
        List<Valuation> valuations = criteria.getValuations();
        Iterator<Valuation> iterator = valuations.iterator();

        while (iterator.hasNext()) {
            Valuation valuation = iterator.next();

            if (valuation.getLineIndex() == valuationToRemove.getLineIndex()) {
                iterator.remove();
            }
        }

        valuationAdapter.update(valuations);
    }

    private void update() {
        valuationAdapter.update(criteria.getValuations());
        criteriaNameView.setText(criteria.getFullName());
    }

    public void setOnValuationClickListener(OnValuationClickListener valuationClickListener) {
        this.valuationClickListener = valuationClickListener;
    }

    public void setOnAddValuationClickListener(OnClickListener addValuationListener) {
        addCriteriaView.setOnClickListener(addValuationListener);
    }
}
