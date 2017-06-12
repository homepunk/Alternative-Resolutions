package homepunk.alternativeresolutions.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.alternativeresolutions.presentation.presenter.CriteriasPresenterImpl;
import homepunk.alternativeresolutions.presentation.presenter.intefaces.CriteriasPresenter;

/**
 * Created by Homepunk on 12.06.2017.
 **/

@Module
public class PresentersModule {
    @Provides
    @Singleton
    CriteriasPresenter providesCriteriasPresenter() {
        return new CriteriasPresenterImpl();
    }
}
