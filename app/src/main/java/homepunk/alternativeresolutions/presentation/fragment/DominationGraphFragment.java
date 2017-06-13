package homepunk.alternativeresolutions.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import homepunk.alternativeresolutions.R;

/**
 * Created by homepunk on 6/14/17.
 */

public class DominationGraphFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_domination_graph, container, false);
    }
}
