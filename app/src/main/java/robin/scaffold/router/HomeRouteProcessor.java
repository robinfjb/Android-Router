package robin.scaffold.router;

import android.content.Context;
import android.content.Intent;

import robin.scaffold.lib.core.AbsRouterProcessor;
import robin.scaffold.lib.core.RouterAction;


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
        } else if("/open/native/home/tab3".equals(path)) {
            action.setTargetType(RouterAction.TYPE_FRAGMENT);
            HomeFragment3 fragment3 = new HomeFragment3();
            action.setTarget(fragment3);
        } else if("/open/native/home".equals(path)) {
            action.setTargetType(RouterAction.TYPE_ACTIVITY);
            Intent intent = new Intent(mContext, HomeActivity.class);
            action.setTarget(intent);
        }else if("/open/native/second".equals(path)) {
            action.setTargetType(RouterAction.TYPE_ACTIVITY);
            Intent intent = new Intent(mContext, SecondActivity.class);
            action.setTarget(intent);
        } else {

        }
    }
}
