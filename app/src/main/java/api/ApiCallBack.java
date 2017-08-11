package api;

/**
 * Created by yangshiyou on 2016/12/15.
 */

public interface ApiCallBack {

    public void onSuccess(boolean isCache, String data);

    public void onFailed(String msg);
}
