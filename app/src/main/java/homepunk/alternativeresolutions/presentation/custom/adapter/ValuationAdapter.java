package homepunk.alternativeresolutions.presentation.custom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.custom.interfaces.OnValuationClickListener;
import homepunk.alternativeresolutions.presentation.viewmodels.Valuation;

/**
 * Created by Homepunk on 13.06.2017.
 **/

public class ValuationAdapter extends RecyclerView.Adapter<ValuationAdapter.ValuationHolder> {
    private Context context;
    private List<Valuation> valuations;
    private OnValuationClickListener valuationClickListener;

    public ValuationAdapter(Context context) {
        this.context = context;
        this.valuations = new ArrayList<>();
    }

    @Override
    public ValuationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_criteria_valuation, parent, false);

        return new ValuationHolder(root);
    }

    @Override
    public void onBindViewHolder(ValuationHolder holder, int position) {
        Valuation valuation = valuations.get(position);
        String valuationName = new StringBuilder()
                .append(valuation.getName())
                .append(valuation.getColumnIndex())
                .append(valuation.getLineIndex())
                .toString();

        holder.setValuationName(valuationName);
        holder.getRoot().setOnClickListener(v -> {
            if (valuationClickListener != null) {
                valuationClickListener.onCriteriaValuationClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return valuations.size();
    }

    public void setOnValuationClickListener(OnValuationClickListener valuationClickListener) {
        this.valuationClickListener = valuationClickListener;
    }

    public void update(Valuation valuation) {
        this.valuations.add(valuation);
        notifyDataSetChanged();
    }

    public void update(List<Valuation> valuations) {
        this.valuations.clear();
        this.valuations.addAll(valuations);
        notifyDataSetChanged();
    }

    class ValuationHolder extends RecyclerView.ViewHolder {
        private TextView valuationName;
        private View root;

        public ValuationHolder(View root) {
            super(root);
            this.root = root;
            valuationName = (TextView) root.findViewById(R.id.item_criteria_valuation_index);
        }

        void setValuationName(String valuationName) {
            this.valuationName.setText(valuationName);
        }

        public View getRoot() {
            return root;
        }
    }
}
