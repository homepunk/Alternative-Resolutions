package homepunk.alternativeresolutions.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.alternativeresolutions.presentation.presenter.DataInputPresenterImpl;
import homepunk.alternativeresolutions.presentation.presenter.DominationGraphPresenterImpl;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DataInputPresenter;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.DominationGraphPresenter;

/**
 * Created by Homepunk on 12.06.2017.
 **/

@Module
public class PresentersModule {
    @Provides
    @Singleton
    DataInputPresenter providesDataInputPresenter() {
        return new DataInputPresenterImpl();
    }

    @Provides
    @Singleton
    DominationGraphPresenter providesDominationGraphPresenter() {
        return new DominationGraphPresenterImpl();
    }
}
