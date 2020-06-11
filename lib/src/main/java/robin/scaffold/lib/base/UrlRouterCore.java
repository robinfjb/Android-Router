package robin.scaffold.lib.base;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import robin.scaffold.lib.exception.RouterException;
import robin.scaffold.lib.util.MathUtils;


/**
 * 处理分发url的实现类
 */

class UrlRouterCore {
    private IRouterConfig routerConfig;
    private SparseArray<Map<String, IProcessInterface>> processorList = new SparseArray<Map<String, IProcessInterface>>();

    public UrlRouterCore(IRouterConfig routerConfig) {
        this.routerConfig = routerConfig;
    }

    public void registerProtocol(String regx, int group, IProcessInterface processor) throws RouterException{
        if(group == 0) {
            if(routerConfig == null) {
                throw new RouterException("please call init method first");
            }
            group = routerConfig.defaultGroup();
        }
        Map<String, IProcessInterface> map = processorList.get(group);
        if(map == null)
            map = new HashMap<String, IProcessInterface>();
        map.put(regx, processor);
        processorList.put(group, map);
    }

    public void unRegisterProtocol(String regx, int group){
        Map<String, IProcessInterface> map = processorList.get(group);
        if(map == null) {
            return;
        }
        map.remove(regx);
        processorList.put(group, map);
    }

    public void unRegisterProtocol(int group){
        processorList.remove(group);
    }

    public IProcessInterface getTargetIProcessor(String url, int group) {
        Map<String, IProcessInterface> map = processorList.get(group);
        if(map == null) {
            return null;
        }
        IProcessInterface processInterface = null;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, IProcessInterface> entry = (Map.Entry<String, IProcessInterface>) iter.next();
            String key = entry.getKey();
            IProcessInterface val = entry.getValue();
            String[] strArray = MathUtils.getMatchStr(url, key);
            if(strArray != null && strArray.length > 0) {
                processInterface = val;
                break;
            }
        }
        return processInterface;
    }
}
