package nice.simplegame.cpedev.simplegame;

import android.content.Context;

/**
 * Created by Christian on 09/02/16.
 */
public class Application extends android.app.Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sContext;
    }
}
