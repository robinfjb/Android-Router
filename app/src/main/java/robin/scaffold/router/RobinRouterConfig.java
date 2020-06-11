package robin.scaffold.router;

import robin.scaffold.lib.base.IRouterConfig;

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
