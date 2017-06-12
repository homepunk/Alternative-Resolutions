package homepunk.alternativeresolutions.presentation.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.viewmodels.Criteria;
import homepunk.alternativeresolutions.presentation.viewmodels.CriteriaValuation;
import homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations;

import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_BAD;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_BETTER;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_GOOD;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_NORMAL;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_THE_BEST;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_THE_WORST;
import static homepunk.alternativeresolutions.presentation.utils.interfaces.Valuations.VALUATION_WORSE;

/**
 * Created by Homepunk on 12.06.2017.
 **/

public class CriteriaLayout extends LinearLayout {
    @BindView(R.id.item_criteria_valuation_the_worst)
    protected LinearLayout valuationTheWorst;
    @BindView(R.id.item_criteria_valuation_worse)
    protected LinearLayout valuationWorse;
    @BindView(R.id.item_criteria_valuation_bad)
    protected LinearLayout valuationBad;
    @BindView(R.id.item_criteria_valuation_normal)
    protected LinearLayout valuationNormal;
    @BindView(R.id.item_criteria_valuation_good)
    protected LinearLayout valuationGood;
    @BindView(R.id.item_criteria_valuation_better)
    protected LinearLayout valuationBetter;
    @BindView(R.id.item_criteria_valuation_the_best)
    protected LinearLayout valuationTheBest;
    @BindView(R.id.item_criteria_name)
    protected TextView criteriaNameTV;

    private Criteria criteria;
    private OnCriteriaValuationClickListener onCriteriaValuationClickListener;

    public CriteriaLayout(Context context) {
        super(context);
        init(context);
    }

    public CriteriaLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CriteriaLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_criteria, this);
        ButterKnife.bind(this, root);
        valuationTheWorst.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public void setOnCriteriaValuationClickListener(OnCriteriaValuationClickListener onCriteriaValuationClickListener) {
        this.onCriteriaValuationClickListener = onCriteriaValuationClickListener;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
        update(criteria);
    }

    public int getValuationsSize() {
        return criteria.getCriteriaValuations().size();
    }

    public void addCriteriaValuation(CriteriaValuation criteriaValuation) {
        @Valuations int valuation = criteriaValuation.getValuation();

        switch (valuation) {
            case VALUATION_THE_WORST: {
                valuationTheWorst.setVisibility(VISIBLE);
                break;
            }
            case VALUATION_WORSE: {
                valuationWorse.setVisibility(VISIBLE);
                break;
            }
            case VALUATION_BAD: {
                valuationBad.setVisibility(VISIBLE);
                break;
            }
            case VALUATION_NORMAL: {
                valuationNormal.setVisibility(VISIBLE);
                break;
            }
            case VALUATION_GOOD: {
                valuationGood.setVisibility(VISIBLE);
                break;
            }
            case VALUATION_BETTER: {
                valuationBetter.setVisibility(VISIBLE);
                break;
            }
            case VALUATION_THE_BEST: {
                valuationTheBest.setVisibility(VISIBLE);
                break;
            }
        }

        criteria.addCriteriaValuation(criteriaValuation);
    }

    public void removeCriteriaValuation(CriteriaValuation criteriaValuation) {
        if (getValuationsSize() > 1) {
            @Valuations int valuation = criteriaValuation.getValuation();

            switch (valuation) {
                case VALUATION_THE_WORST: {
                    valuationTheWorst.setVisibility(GONE);
                    break;
                }
                case VALUATION_WORSE: {
                    valuationWorse.setVisibility(GONE);
                    break;
                }
                case VALUATION_BAD: {
                    valuationBad.setVisibility(GONE);
                    break;
                }
                case VALUATION_NORMAL: {
                    valuationNormal.setVisibility(GONE);
                    break;
                }
                case VALUATION_GOOD: {
                    valuationGood.setVisibility(GONE);
                    break;
                }
                case VALUATION_BETTER: {
                    valuationBetter.setVisibility(GONE);
                    break;
                }
                case VALUATION_THE_BEST: {
                    valuationTheBest.setVisibility(GONE);
                    break;
                }
            }

            criteria.removeCriteriaValuation(criteriaValuation);
        }
    }

    private void update(Criteria newCriteria) {
        if (criteria != null) {
            List<CriteriaValuation> newValuations = newCriteria.getCriteriaValuations();
            List<CriteriaValuation> oldValuations = criteria.getCriteriaValuations();

            if (oldValuations.size() != newValuations.size()) {
                int differnce = newValuations.size() - oldValuations.size();

                if (differnce > 0) {
                    for (CriteriaValuation newCriteriaValuation : newCriteria.getCriteriaValuations()) {
                            int newValuation = newCriteriaValuation.getValuation();

                            if(!oldValuations.contains(newValuation)) {
                                addCriteriaValuation(newCriteriaValuation);
                            }
                    }
                } else if (differnce < 0){
                    for (CriteriaValuation newCriteriaValuation : newCriteria.getCriteriaValuations()) {
                        int newValuation = newCriteriaValuation.getValuation();

                        if(oldValuations.contains(newValuation)) {
                            removeCriteriaValuation(newCriteriaValuation);
                        }
                    }
                }
            }

            criteriaNameTV.setText(criteria.getName() + criteria.getIndex());
        }
    }
}
