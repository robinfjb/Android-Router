package robin.scaffold.lib.base;

import robin.scaffold.lib.core.RouterConfig;

public interface IRouterConfig extends RouterConfig {
    String getScheme();
    String getHost();
    String getPackageName();
    int defaultGroup();
}
