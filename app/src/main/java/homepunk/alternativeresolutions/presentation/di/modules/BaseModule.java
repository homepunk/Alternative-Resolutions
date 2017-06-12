package homepunk.alternativeresolutions.presentation.di.modules;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Homepunk on 12.06.2017.
 **/

@Module
public class BaseModule {
    private final Context context;

    public BaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    public LayoutInflater provideLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }
}
