package robin.scaffold.lib.base;


import robin.scaffold.lib.exception.RouterException;

/**
 * 负责url的预处理
 * @param <T>
 */
public interface IPreProcessInterface<T> {
    T prePorcess(String url) throws RouterException;
}
