package server;

import java.util.Map;

import api.ApiCallBack;
import api.model.Response;

/**
 * Created by yangshiyou on 2016/12/20.
 */

public interface IServer {


    /**
     * 设置服务器地址
     *
     * @param url
     */
    public void setUrl(String url);


    /**
     * 设置方法名称(如果有模块名称:/user/login)
     *
     * @param methodName
     */
    public void setMethodName(String methodName);

    /**
     * 设置参数
     *
     * @param key
     * @param value
     */
    public void setParam(String key, Object value);

    /**
     * 设置回调
     *
     * @param apiCallBack
     */
    public void setApiCallBack(ApiCallBack apiCallBack);

    /**
     * 设置是否使用缓存(全局设置缓存时间)
     *
     * @param useCache
     */
    public void setUseCache(boolean useCache);

    /**
     * 设置是否使用缓存(指定缓存时间)
     *
     * @param useCache
     * @param cacheTime 缓存时间(s)
     */
    public void setUseCache(boolean useCache, int cacheTime);


    /**
     * 执行网络请求
     */
    public void request();

}
