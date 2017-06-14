package homepunk.alternativeresolutions.presentation.di;

import javax.inject.Singleton;

import dagger.Component;
import homepunk.alternativeresolutions.presentation.activity.DataInputActivity;
import homepunk.alternativeresolutions.presentation.di.modules.BaseModule;
import homepunk.alternativeresolutions.presentation.di.modules.PresentersModule;
import homepunk.alternativeresolutions.presentation.fragment.DominationGraphFragment;

/**
 * Created by Homepunk on 12.06.2017.
 **/

@Component(modules = {BaseModule.class, PresentersModule.class})
@Singleton
public interface BaseComponent {
    void inject(DataInputActivity activity);

    void inject(DominationGraphFragment fragment);
}
