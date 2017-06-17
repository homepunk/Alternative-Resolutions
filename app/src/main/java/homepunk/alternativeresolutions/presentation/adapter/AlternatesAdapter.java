package homepunk.alternativeresolutions.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.custom.interfaces.SimpleClickListener;
import homepunk.alternativeresolutions.presentation.models.Alternate;
import homepunk.alternativeresolutions.presentation.models.Valuation;

/**
 * Created by homepunk on 6/17/17.
 */

public class AlternatesAdapter extends RecyclerView.Adapter<AlternatesAdapter.AlternateHolder> {
    private Context context;
    private List<Alternate> alternates;

    public AlternatesAdapter(Context context) {
        this.context = context;
        this.alternates= new ArrayList<>();
    }

    @Override
    public AlternateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_alternate, parent, false);

        return new AlternateHolder(root);
    }

    @Override
    public void onBindViewHolder(AlternateHolder holder, int position) {
        Alternate alternate = alternates.get(position);
        Valuation first = alternate.getAlternate().first;
        Valuation second = alternate.getAlternate().second;

        holder.setFirstValuationName(first.getFullName());
        holder.setSecondValuationName(second.getFullName());
    }

    @Override
    public int getItemCount() {
        return alternates.size();
    }

    public void setAlternates(List<Alternate> alternates) {
        this.alternates.addAll(alternates);
        notifyDataSetChanged();
    }

    public class AlternateHolder extends RecyclerView.ViewHolder {
        private TextView secondValuationName;
        private TextView firstValuationName;

        public AlternateHolder(View root) {
            super(root);
            firstValuationName = (TextView) root.findViewById(R.id.item_alternate_first_valuation_name);
            secondValuationName = (TextView) root.findViewById(R.id.item_alternate_second_valuation_name);
        }

        void setFirstValuationName(String firstValuationName) {
            this.firstValuationName.setText(firstValuationName);
        }

        void setSecondValuationName(String secondValuationName) {
            this.secondValuationName.setText(secondValuationName);
        }

    }
}
