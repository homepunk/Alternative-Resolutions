package homepunk.alternativeresolutions.presentation.base;

import android.app.Application;

import homepunk.alternativeresolutions.presentation.di.BaseComponent;
import homepunk.alternativeresolutions.presentation.di.DaggerBaseComponent;
import homepunk.alternativeresolutions.presentation.di.modules.BaseModule;
import homepunk.alternativeresolutions.presentation.di.modules.PresentersModule;
import timber.log.Timber;

/**
 * Created by Homepunk on 12.06.2017.
 **/

public class BaseApp extends Application {
    private static BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        baseComponent = DaggerBaseComponent.builder()
                .baseModule(new BaseModule(this))
                .presentersModule(new PresentersModule())
                .build();

        Timber.plant(new Timber.DebugTree());
    }

    public static BaseComponent getBaseComponent() {
        return baseComponent;
    }
}
