package homepunk.alternativeresolutions.presentation.view;

import java.util.List;

import homepunk.alternativeresolutions.presentation.models.Alternate;
import homepunk.alternativeresolutions.presentation.models.DominationGraph;

/**
 * Created by homepunk on 6/14/17.
 */

public interface DominationGraphView extends View {
    void buildDominationGraph(DominationGraph dominationGraph);

    void showBestAlternate(Alternate alternate);

    void showWorstAlternate(Alternate alternate);

    void showBetterAlternates(List<Alternate> alternates);

    void showWorseAlternates(List<Alternate> alternates);

    void showIncomparableAlternates(List<Alternate> alternates);
}
