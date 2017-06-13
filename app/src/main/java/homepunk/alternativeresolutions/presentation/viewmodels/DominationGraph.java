package homepunk.alternativeresolutions.presentation.viewmodels;

import android.support.v4.util.Pair;

import java.util.TreeSet;

/**
 * Created by homepunk on 6/14/17.
 */

public class DominationGraph {
    private TreeSet<Alternate> alternatesTree;

    public TreeSet<Alternate> getAlternatesTree() {
        return alternatesTree;
    }

    public void setAlternatesTree(TreeSet<Alternate> alternatesTree) {
        this.alternatesTree = alternatesTree;
    }
}
