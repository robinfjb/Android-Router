package robin.scaffold.router;

import android.app.Application;

import robin.scaffold.lib.base.ProcessorRegister;

public class DemoApplication extends Application {
    private ProcessorRegister processorRegister;
    @Override
    public void onCreate() {
        super.onCreate();

        //注册
        processorRegister = new HomeModuleRegister();
        processorRegister.register();
    }
}
