package robin.scaffold.lib.core.handler;

import android.content.Context;

import robin.scaffold.lib.base.IResultCallback;
import robin.scaffold.lib.core.RouterAction;
import robin.scaffold.lib.exception.RouterException;


public interface IRouterHandler {
    boolean handle(Context context, RouterAction action, IResultCallback callback) throws RouterException;
}
