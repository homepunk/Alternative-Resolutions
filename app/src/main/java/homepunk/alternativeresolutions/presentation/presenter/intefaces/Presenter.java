package homepunk.alternativeresolutions.presentation.presenter.intefaces;

import homepunk.alternativeresolutions.presentation.view.View;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public interface Presenter <T extends View> {
    void init(T view);

    void terminate();
}
