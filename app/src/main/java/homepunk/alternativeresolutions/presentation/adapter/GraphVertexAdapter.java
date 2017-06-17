package homepunk.alternativeresolutions.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.models.Alternate;
import homepunk.alternativeresolutions.presentation.models.DominationGraph;
import homepunk.alternativeresolutions.presentation.models.Valuation;

/**
 * Created by Homepunk on 14.06.2017.
 **/

public class GraphVertexAdapter extends RecyclerView.Adapter<GraphVertexAdapter.VertexHolder> {
    private DominationGraph dominationGraph;
    private List<Alternate> alternates;

    public GraphVertexAdapter() {
        alternates = new ArrayList<>();
    }

    @Override
    public VertexHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_graph_vertex, parent, false);

        return new VertexHolder(root);
    }

    @Override
    public void onBindViewHolder(VertexHolder holder, int position) {
        Alternate alternate = alternates.get(position);
        Valuation first = alternate.getAlternate().first;
        Valuation second = alternate.getAlternate().second;

        if (first.getLineIndex() < dominationGraph.getColumnsCount()) {
            holder.showDownwardArrow(true);
        } else {
            holder.showDownwardArrow(false);
        }

        if (second.getLineIndex() < dominationGraph.getLinesCount()) {
            holder.showForwardArrow(true);
        } else {
            holder.showForwardArrow(false);
        }

        holder.setFirstVertexName(first.getFullName());
        holder.setSecondVertexName(second.getFullName());
    }

    @Override
    public int getItemCount() {
        return alternates.size();
    }

    public void setDominationGraph(DominationGraph dominationGraph) {
        this.dominationGraph = dominationGraph;
        this.alternates = dominationGraph.getAlternates();
        notifyDataSetChanged();
    }

    class VertexHolder extends RecyclerView.ViewHolder {
        private TextView firstVertexName;
        private TextView secondVertexName;
        private ImageView forwardArrow;
        private ImageView downwardArrow;

        VertexHolder(View root) {
            super(root);

            firstVertexName = (TextView) root.findViewById(R.id.item_alternate_first_valuation_name);
            secondVertexName = (TextView) root.findViewById(R.id.item_alternate_second_valuation_name);
            forwardArrow = (ImageView) root.findViewById(R.id.item_graph_vertex_forward_arrow);
            downwardArrow= (ImageView) root.findViewById(R.id.item_graph_vertex_downward_arrow);
        }

        void setFirstVertexName(String vertexName) {
            this.firstVertexName.setText(vertexName);
        }

        void setSecondVertexName(String vertexName) {
            this.secondVertexName.setText(vertexName);
        }

        void showForwardArrow(boolean show) {
            if (show) {
                forwardArrow.setVisibility(View.VISIBLE);
            } else {
                forwardArrow.setVisibility(View.GONE);
            }
        }

        void showDownwardArrow(boolean show) {
            if (show) {
                downwardArrow.setVisibility(View.VISIBLE);
            } else {
                downwardArrow.setVisibility(View.GONE);
            }
        }
    }
}
