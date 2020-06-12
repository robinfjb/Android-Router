package robin.scaffold.router;


import robin.scaffold.lib.base.ProcessorRegister;
import robin.scaffold.lib.base.UrlRouteManager;
import robin.scaffold.lib.exception.RouterException;

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

    @Override
    public void unregister() {
        try {
            UrlRouteManager.getInstance().unRegisterProtocol(group);
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }
}
