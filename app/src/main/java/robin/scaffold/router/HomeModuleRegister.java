package robin.scaffold.router;


import robin.scaffold.lib.base.ProcessorRegister;
import robin.scaffold.lib.base.UrlRouteManager;
import robin.scaffold.lib.robin.RobinRouterConfig;

public class HomeModuleRegister extends ProcessorRegister {
    private String regex1 = "crf://crfchina.com/open/native/home/tab1";
    private String regex2 = "crf://crfchina.com/open/native/home/tab2";
    private String regex3 = "crf://crfchina.com/open/native/home/tab3";
    private String regex4 = "crf://crfchina.com/open/native/home";
    private String regex5 = "crf://crfchina.com/open/native/second";
    private int group = RobinRouterConfig.GROUP_HOME;
    @Override
    public void register() {
        HomeRouteProcessor processor = new HomeRouteProcessor();
        UrlRouteManager.getInstance().registerProtocol(regex1, group, processor);
        UrlRouteManager.getInstance().registerProtocol(regex2, group, processor);
        UrlRouteManager.getInstance().registerProtocol(regex3, group, processor);
        UrlRouteManager.getInstance().registerProtocol(regex4, group, processor);
        UrlRouteManager.getInstance().registerProtocol(regex5, group, processor);
    }

    @Override
    public void unregister() {
        UrlRouteManager.getInstance().unRegisterProtocol(group);
    }
}
