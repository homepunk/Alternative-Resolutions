package homepunk.alternativeresolutions.presentation.models;

import java.util.ArrayList;
import java.util.List;

import static homepunk.alternativeresolutions.presentation.data.Constants.NAME_TWO_DIMENSIONS_DOMINATION_GRAPH;

/**
 * Created by homepunk on 6/14/17.
 */

public class DominationGraph {
    private String name;
    private int linesCount;
    private int columnsCount;
    private Alternate[][] alternatesMatrix;
    private List<Criterion> criteria;
    private List<Alternate> alternates;

    public DominationGraph(List<Criterion> criteria) {
        this.columnsCount = criteria.get(0).getValuations().size();
        this.linesCount = criteria.get(1).getValuations().size();
        this.criteria = criteria;
        this.alternatesMatrix = new Alternate[columnsCount][linesCount];
        this.name = NAME_TWO_DIMENSIONS_DOMINATION_GRAPH;

        initAlternates();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public void setColumnsCount(int columnsCount) {
        this.columnsCount = columnsCount;
    }

    public Alternate[][] getAlternatesMatrix() {
        return alternatesMatrix;
    }

    public void setAlternatesMatrix(Alternate[][] alternatesMatrix) {
        this.alternatesMatrix = alternatesMatrix;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterion> criteria) {
        this.criteria = criteria;
    }

    public List<Alternate> getAlternates() {
        return alternates;
    }

    public void setAlternates(List<Alternate> alternates) {
        this.alternates = alternates;
    }

    public void buildDominationGraph() {
        int expectedSize = columnsCount * linesCount;
        int index = 0;

        for (int column = 0; column < columnsCount; column++) {
            for (int line = 0; line < linesCount; line++) {
                if (index <= expectedSize) {
                    alternatesMatrix[column][line] = alternates.get(index);
                    index++;
                }
            }
        }
    }

    private void initAlternates() {
        alternates = new ArrayList<>();
        for (Valuation first : criteria.get(0).getValuations()) {
            for (Valuation second : criteria.get(1).getValuations()) {
                alternates.add(new Alternate(first, second));
            }
        }
    }
}
