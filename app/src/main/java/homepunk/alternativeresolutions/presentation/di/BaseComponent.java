package homepunk.alternativeresolutions.presentation.di;

import javax.inject.Singleton;

import dagger.Component;
import homepunk.alternativeresolutions.presentation.activity.CriteriasActivity;
import homepunk.alternativeresolutions.presentation.di.modules.BaseModule;
import homepunk.alternativeresolutions.presentation.di.modules.PresentersModule;

/**
 * Created by Homepunk on 12.06.2017.
 **/

@Component(modules = {BaseModule.class, PresentersModule.class})
@Singleton
public interface BaseComponent {
    void inject(CriteriasActivity activity);
}
