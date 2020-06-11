package robin.scaffold.lib.base;

import android.content.Context;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.io.Serializable;

import robin.scaffold.lib.core.handler.IRouterHandler;
import robin.scaffold.lib.exception.RouterException;


/**
 * 负责url的处理
 * @param <T>
 */

public interface IProcessInterface<T> {
    boolean execute(Context context, String url, @Nullable IRouterHandler handler, IResultCallback callback, IRouterConfig routerConfig) throws RouterException;
    /**
     * 支持Serializable对象传递
     * @param obj
     */
    void withSerializableObj(Serializable obj);
    /**
     * 支持Parcelable对象传递
     * @param obj
     */
    void withParcelableObj(Parcelable obj);
}
