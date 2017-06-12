package homepunk.alternativeresolutions.presentation.base;

import homepunk.alternativeresolutions.presentation.presenter.intefaces.Presenter;
import homepunk.alternativeresolutions.presentation.view.View;

/**
 * Created by Homepunk on 06.06.2017.
 **/

public abstract class BasePresenter<T extends View> implements Presenter<T> {
    protected T view;

    @Override
    public void init(T view) {
        this.view = view;
    }

    @Override
    public void terminate() {
        this.view = null;
    }
}
