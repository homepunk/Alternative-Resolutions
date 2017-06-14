package homepunk.alternativeresolutions.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import homepunk.alternativeresolutions.R;
import homepunk.alternativeresolutions.presentation.adapter.GraphVertexAdapter;
import homepunk.alternativeresolutions.presentation.base.BaseApp;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DominationGraphPresenter;
import homepunk.alternativeresolutions.presentation.view.DominationGraphView;
import homepunk.alternativeresolutions.presentation.viewmodels.Alternate;
import homepunk.alternativeresolutions.presentation.viewmodels.Criterion;
import homepunk.alternativeresolutions.presentation.viewmodels.DominationGraph;
import timber.log.Timber;

import static homepunk.alternativeresolutions.presentation.data.Constants.DOMINATION_GRAPH_FRAGMENT_KEY;
import static homepunk.alternativeresolutions.presentation.data.Constants.KEY_SELECTED_ALTERNATE;

/**
 * Created by homepunk on 6/14/17.
 */

public class DominationGraphFragment extends Fragment implements DominationGraphView {
    @Inject
    DominationGraphPresenter presenter;

    @BindView(R.id.fragment_domination_graph_recycler)
    RecyclerView graphRecycler;

    private List<Criterion> criteria;
    private Alternate selectedAlternate;
    private GraphVertexAdapter vertexAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_domination_graph, container, false);

        init(root);
        initUI();
        retrieveCriteriaFromBundle();
        Timber.i("Selected alternate: " + selectedAlternate.toString());

        presenter.designDominationGraph(criteria);

        return root;
    }

    @Override
    public void buildDominationGraph(DominationGraph dominationGraph) {
        graphRecycler.setLayoutManager(new GridLayoutManager(getContext(), dominationGraph.getLinesCount()));
        vertexAdapter.setDominationGraph(dominationGraph);

        presenter.onDominationGraphBuilt();
    }

    private void init(View root) {
        BaseApp.getBaseComponent().inject(this);
        ButterKnife.bind(this, root);
        presenter.init(this);
    }

    private void initUI() {
        vertexAdapter = new GraphVertexAdapter();
        graphRecycler.setAdapter(vertexAdapter);
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
