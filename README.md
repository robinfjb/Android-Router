# Android-Router
Android Router Project 路由框架

### 第一步，定义
定义自己的Config类
主要定义Scheme，host，可以自定义group，group是为了支持重复的url，如果未自定义，则默认使用default
```
public class RobinRouterConfig implements IRouterConfig {
    public static final String SCHEME = "robin";
    public static final String HOST = "robin.test";
    public static final String MY_PACKAGE_NAME = "robin.scaffold.router";

    public static final int GROUP_DEFAULT = 0x01;
    public static final int GROUP_HOME = 0x02;

    @Override
    public String getScheme() {
        return SCHEME;
    }

    @Override
    public String getHost() {
        return HOST;
    }

    @Override
    public String getPackageName() {
        return MY_PACKAGE_NAME;
    }

    @Override
    public int defaultGroup() {
        return GROUP_DEFAULT;
    }
}

```

### 第二步 注册
定义自己的注册类
务必将所有的url都到此类注册

```
public class HomeModuleRegister extends ProcessorRegister {
    private String regex1 = RobinRouterConfig.SCHEME + "://" + RobinRouterConfig.HOST + "/open/native/home/tab1";
    private String regex2 = RobinRouterConfig.SCHEME + "://" + RobinRouterConfig.HOST + "/open/native/home/tab2";
    private String regex3 = RobinRouterConfig.SCHEME + "://" + RobinRouterConfig.HOST + "/open/native/home/tab3";
    private String regex4 = RobinRouterConfig.SCHEME + "://" + RobinRouterConfig.HOST + "/open/native/home";
    private String regex5 = RobinRouterConfig.SCHEME + "://" + RobinRouterConfig.HOST + "/open/native/second";
    private String regex6 = RobinRouterConfig.SCHEME + "://" + RobinRouterConfig.HOST + "/open/web";
    private int group = RobinRouterConfig.GROUP_HOME;
    @Override
    public void register() {
        HomeRouteProcessor processor = new HomeRouteProcessor();
        UrlRouteManager.getInstance().init(new RobinRouterConfig());
        try {
            UrlRouteManager.getInstance().registerProtocol(regex1, group, processor);
            UrlRouteManager.getInstance().registerProtocol(regex2, group, processor);
            UrlRouteManager.getInstance().registerProtocol(regex3, group, processor);
            UrlRouteManager.getInstance().registerProtocol(regex4, group, processor);
            UrlRouteManager.getInstance().registerProtocol(regex5, group, processor);
            UrlRouteManager.getInstance().registerProtocol(regex6, group, processor);
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }
}
```
注意：不要忘记初始化：UrlRouteManager.getInstance().init(new RobinRouterConfig());此句是为了告诉UrlRouteManager定义的Scheme，host等信息

然后再application注册他：
```
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
```
### 第三步 处理器
第二步中需要一个HomeRouteProcessor对象：
此对象是为了mapping所有path的规则，如果需要自己处理，可以不在此处mapping，后面会讲到
```
public class HomeRouteProcessor extends AbsRouterProcessor {
    @Override
    protected void mapping(Context context, RouterAction action) {
        String path = action.getPath();
        if("/open/native/home/tab1".equals(path)) {
            action.setTargetType(RouterAction.TYPE_FRAGMENT);
            HomeFragment1 fragment1 = new HomeFragment1();
            action.setTarget(fragment1);
        } else if("/open/native/home/tab2".equals(path)) {
            action.setTargetType(RouterAction.TYPE_FRAGMENT);
            HomeFragment2 fragment2 = new HomeFragment2();
            action.setTarget(fragment2);
        }
        ...
    }
}
```

### 第四步 使用
#### 1. 带参数Activity 跳转：
```
 String testUrl = "robin://robin.test/open/native/second?param=test001";
        try {
            new RouterExcuter().execute(MainActivity.this, testUrl, RobinRouterConfig.GROUP_HOME, null, null);
        } catch (RouterException e) {
            e.printStackTrace();
        }
```
目标Activity接收：intent.getStringExtra("param")即可获取参数值

##### 2. 带requestCode跳转：
加上withRequestCode方法即可
```
 String testUrl = "robin://robin.test/open/native/home";
        try {
            new RouterExcuter().withRequestCode(1000).execute(MainActivity.this, testUrl, RobinRouterConfig.GROUP_HOME, null, null);
        } catch (RouterException e) {
            e.printStackTrace();
        }
```

#### 3. fragment跳转：
需要自定义callback，获取到目标fragment后自行处理
```
try {
            new RouterExcuter()
                    .withSerializableObj(data)
                    .execute(this, ""robin://robin.test/open/native/home/tab1?param=test1"", RobinRouterConfig.GROUP_HOME, null, new IResultCallback() {
                        @Override
                        public void onResult(Object fragment) {
                            if(fragment != null) {
                                if(!(fragment instanceof Fragment)) {
                                    return;
                                }
                                addFragmentToActivity()
                            }
                        }
                    });
        } catch (RouterException e) {
            e.printStackTrace();
        }
```

#### 4. 自定义处理：
不由框架做跳转或者返回fragment
比如要打开一个外部http url链接,可以自定义一个IRouterHandler处理器
```
String testUrl = "robin://robin.test/open/web?url=https://m.baidu.com";
        try {
            new RouterExcuter().withRequestCode(1000).execute(MainActivity.this, testUrl, RobinRouterConfig.GROUP_HOME, new IRouterHandler() {
                @Override
                public boolean handle(Context context, RouterAction action, IResultCallback callback) throws RouterException {
                    Log.d("MainActivity", "action内容：" + action.toString());
                    if("/open/web".equals(action.getPath())) {
                        String url = action.getParameters().getParameter("url");
                        if(!TextUtils.isEmpty(url)) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            context.startActivity(intent);
                        }
                    }
                    return true;
                }
            }, null);
        } catch (RouterException e) {
            e.printStackTrace();
        }
```


