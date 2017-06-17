package homepunk.alternativeresolutions.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.adapter.AlternatesAdapter;
import homepunk.alternativeresolutions.presentation.adapter.GraphVertexAdapter;
import homepunk.alternativeresolutions.presentation.base.BaseApp;
import homepunk.alternativeresolutions.presentation.models.Valuation;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DominationGraphPresenter;
import homepunk.alternativeresolutions.presentation.view.DominationGraphView;
import homepunk.alternativeresolutions.presentation.models.Alternate;
import homepunk.alternativeresolutions.presentation.models.Criterion;
import homepunk.alternativeresolutions.presentation.models.DominationGraph;
import timber.log.Timber;

import static homepunk.alternativeresolutions.presentation.data.Constants.DOMINATION_GRAPH_FRAGMENT_KEY;
import static homepunk.alternativeresolutions.presentation.data.Constants.KEY_SELECTED_ALTERNATE;
import static timber.log.Timber.i;

/**
 * Created by homepunk on 6/14/17.
 */

public class DominationGraphFragment extends Fragment implements DominationGraphView {
    @Inject
    DominationGraphPresenter presenter;

    @BindView(R.id.fragment_domination_graph_recycler)
    protected RecyclerView graphRecycler;
    @BindView(R.id.fragment_domination_graph_better_alternates_recycler)
    protected RecyclerView betterAlternatesRecycler;
    @BindView(R.id.fragment_domination_graph_worse_alternates_recycler)
    protected RecyclerView worseAlternatesRecycler;
    @BindView(R.id.fragment_domination_graph_incomparable_alternates_recycler)
    protected RecyclerView incomparableAlternatesRecycler;
    @BindView(R.id.best_alternate_container)
    protected LinearLayout bestAlternatesContainer;
    @BindView(R.id.worst_alternate_container)
    protected LinearLayout worstAlternatesContainer;
    @BindView(R.id.item_best_alternate_first_valuation_name)
    protected TextView bestAlternateFirstValuationName;
    @BindView(R.id.item_best_alternate_second_valuation_name)
    protected TextView bestAlternateSecondValuationName;
    @BindView(R.id.item_worst_alternate_first_valuation_name)
    protected TextView worstAlternateFirstValuationName;
    @BindView(R.id.item_worst_alternate_second_valuation_name)
    protected TextView worstAlternateSecondValuationName;

    private Alternate selectedAlternate;
    private List<Criterion> criteria;
    private GraphVertexAdapter vertexAdapter;
    private AlternatesAdapter worseAlternatesAdapter;
    private AlternatesAdapter betterAlternatesAdapter;
    private AlternatesAdapter incomparableAlternatesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_domination_graph, container, false);

        init(root);
        initUI();
        retrieveCriteriaFromBundle();

        presenter.setSelectedAlternate(selectedAlternate);
        presenter.designDominationGraph(criteria);

        return root;
    }

    @Override
    public void buildDominationGraph(DominationGraph dominationGraph) {
        graphRecycler.setLayoutManager(new GridLayoutManager(getContext(), dominationGraph.getLinesCount()));
        vertexAdapter.setDominationGraph(dominationGraph);

        presenter.onDominationGraphBuilt();
    }

    @Override
    public void showBestAlternate(Alternate alternate) {
        if (alternate != null) {
            bestAlternatesContainer.setVisibility(View.VISIBLE);
            bestAlternateFirstValuationName.setText(alternate.getAlternate().first.getFullName());
            bestAlternateSecondValuationName.setText(alternate.getAlternate().second.getFullName());
        }
    }

    @Override
    public void showWorstAlternate(Alternate alternate) {
        if (alternate != null) {
            worstAlternatesContainer.setVisibility(View.VISIBLE);
            worstAlternateFirstValuationName.setText(alternate.getAlternate().first.getFullName());
            worstAlternateSecondValuationName.setText(alternate.getAlternate().second.getFullName());
        }
    }

    @Override
    public void showBetterAlternates(List<Alternate> alternates) {
        if (alternates.size() != 0) {
            betterAlternatesAdapter.setAlternates(alternates);
        }
    }

    @Override
    public void showWorseAlternates(List<Alternate> alternates) {
        if (alternates.size() != 0) {
            worseAlternatesAdapter.setAlternates(alternates);
        }
    }

    @Override
    public void showIncomparableAlternates(List<Alternate> alternates) {
        if (alternates.size() != 0) {
            incomparableAlternatesAdapter.setAlternates(alternates);
        }
    }


    private void init(View root) {
        BaseApp.getBaseComponent().inject(this);
        ButterKnife.bind(this, root);
        presenter.init(this);
    }

    private void initUI() {
        vertexAdapter = new GraphVertexAdapter();
        graphRecycler.setAdapter(vertexAdapter);

        betterAlternatesAdapter = new AlternatesAdapter(getContext());
        betterAlternatesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        betterAlternatesRecycler.setAdapter(betterAlternatesAdapter);

        worseAlternatesAdapter = new AlternatesAdapter(getContext());
        worseAlternatesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        worseAlternatesRecycler.setAdapter(worseAlternatesAdapter);

        incomparableAlternatesAdapter = new AlternatesAdapter(getContext());
        incomparableAlternatesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        incomparableAlternatesRecycler.setAdapter(incomparableAlternatesAdapter);
    }

    private void retrieveCriteriaFromBundle() {
        Bundle bundle = getArguments();

        if (bundle != null) {
            criteria = (List<Criterion>) bundle.getSerializable(DOMINATION_GRAPH_FRAGMENT_KEY);
            selectedAlternate = (Alternate) bundle.getSerializable(KEY_SELECTED_ALTERNATE);
        } else {
            criteria = new ArrayList<>();
        }
    }
}
