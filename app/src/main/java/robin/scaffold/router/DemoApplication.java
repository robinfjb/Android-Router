package robin.scaffold.router;

import android.app.Application;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //注册
        new HomeModuleRegister().register();
    }
}
