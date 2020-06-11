package robin.scaffold.lib.base;


import robin.scaffold.lib.exception.RouterException;

/**
 * router功能对外类
 */

public class UrlRouteManager {
    private static class SingletonHolder {
        private static final UrlRouteManager INSTANCE = new UrlRouteManager();
    }
    private UrlRouterCore core;
    private IRouterConfig routerConfig;
    private UrlRouteManager (){}
    public static final UrlRouteManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(IRouterConfig config) {
        routerConfig = config;
        core = new UrlRouterCore(routerConfig);
    }

    public IRouterConfig getRouterConfig() {
        return routerConfig;
    }

    /**
     * 注册
     * @param path
     * @param processor
     */
    public void registerProtocol(String path, int group, IProcessInterface processor) throws RouterException{
        if(core == null) {
            throw new RouterException("please call init method first");
        }
        core.registerProtocol(path, group, processor);
    }

    public void registerProtocol(String path, IProcessInterface processor) throws RouterException{
        if(routerConfig == null || core == null) {
            throw new RouterException("please call init method first");
        }
        core.registerProtocol(path, routerConfig.defaultGroup(), processor);
    }

    /**
     * 反注册
     * @param path
     * @param group
     */
    public void unRegisterProtocol(String path, int group) throws RouterException{
        if(core == null) {
            throw new RouterException("please call init method first");
        }
        core.unRegisterProtocol(path, group);
    }

    /**
     * 反注册整个group
     * @param group
     */
    public void unRegisterProtocol(int group) throws RouterException{
        if(core == null) {
            throw new RouterException("please call init method first");
        }
        core.unRegisterProtocol(group);
    }

    /**
     * 查找Processor
     * @param url
     * @param group
     * @return
     */
    public IProcessInterface seek(String url, int group) throws RouterException{
        if(core == null) {
            throw new RouterException("please call init method first");
        }
        return core.getTargetIProcessor(url, group);
    }

    public IProcessInterface seek(String url) throws RouterException{
        if(routerConfig == null || core == null) {
            throw new RouterException("please call init method first");
        }
        return core.getTargetIProcessor(url, routerConfig.defaultGroup());
    }
}
