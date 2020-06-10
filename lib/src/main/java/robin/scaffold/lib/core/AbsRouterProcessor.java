package robin.scaffold.lib.core;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;

import robin.scaffold.lib.base.IProcessInterface;
import robin.scaffold.lib.base.IResultCallback;
import robin.scaffold.lib.core.handler.IRouterHandler;
import robin.scaffold.lib.core.handler.RouterHandlerFactory;
import robin.scaffold.lib.exception.RouterException;


/**
 * router处理抽象类，定义了处理流程
 */
public abstract class AbsRouterProcessor implements IProcessInterface<RouterAction> {
    protected static final String TAG = "AbsRouterProcessor";
    //跳转源
    protected Context mContext;
    /**
     * 是否成功处理
     */
    protected boolean success;

    protected Bundle bundle;
    protected RouterAction mAction;

    @Nullable
    public RouterAction getAction() {
        return mAction;
    }


    //====================================================
    // 对外接口方法
    //====================================================
    /***
     * 用于自定义scheme跳转。在处理上，open = preprocess生成intent + 用生成intent启动activity
     */
    @Override
    public boolean execute(Context context, String url, IRouterHandler handler, IResultCallback callback) throws RouterException{
        mContext = context;
        RouterAction action = processUrl(url);
        return processResult(action, handler, callback);
    }

    @Override
    public void withSerializableObj(Serializable obj) {
        if(bundle == null)
            bundle = new Bundle();
        bundle.putSerializable("s_obj",obj);
    }

    @Override
    public void withParcelableObj(Parcelable obj) {
        if(bundle == null)
            bundle = new Bundle();
        bundle.putParcelable("p_obj",obj);
    }

    protected RouterAction preprocess(String url) throws RouterException{
        CommPreProcessor preProcessor = new CommPreProcessor(mContext);
        RouterAction action = preProcessor.prePorcess(url);
        return action;
    }

    protected void process(RouterAction action) throws RouterException{
        mapping(mContext, action);
        action.setBundle(bundle);
    }

    protected boolean handleRouteAction(RouterAction action, IRouterHandler handler, IResultCallback callback) {
        if(action == null) {
            return false;
        }
        if(handler == null)//如果没有指定处理器，用系统自带的
            handler = new RouterHandlerFactory().createHandler(action);
        if(handler == null) {
            return false;
        }
        try {
            return handler.handle(mContext, action, callback);
        } catch (RouterException e) {
            e.printStackTrace();
        }
        return false;
    }

    private RouterAction processUrl(String url) throws RouterException{
        RouterAction action = preprocess(url);
        process(action);
        return action;
    }

    private boolean processResult(RouterAction action, IRouterHandler handler, IResultCallback callback) {
        if (action == null)
            return success;
        success = handleRouteAction(action, handler, callback);
        return success;
    }

    abstract protected void mapping(Context context, RouterAction action) throws RouterException;
}
