package api;

/**
 * Created by yangshiyou on 2016/12/20.
 */

public class Config {

    private static boolean isDebug = false;

    //http://101.201.238.253:8080/yljy/sys/sysuser/login?phone=admin&password=123456

    /**
     * 服务器地址
     */
    private static String server_url;

    /**
     * 服务器端口
     */
    private static int server_port;

    /**
     * 服务器项目名称
     */
    private static String projectName;

    /**
     * 网络请求缓存时间(秒);
     */
    private static int cacheMaxAge = 1000 * 60;


    /**
     * 设置调试模式；默认为调试模式
     *
     * @param isDebug
     */
    public static void setIsDebug(boolean isDebug) {

        Config.isDebug = isDebug;

        if (isDebug) {//如果是调试

            Config.server_url = "192.168.1.105";

            Config.server_port = 8080;

            Config.projectName = "yljy";


        } else {//正式

            Config.server_url = "101.201.238.253";

            Config.server_port = 8080;

            Config.projectName = "yrkdy";

        }
    }


    public static String getServer_url() {
        setIsDebug(isDebug);
        return String.format("http://%s:%d/%s/", server_url, server_port, projectName);
    }

    public static int getCacheMaxAge() {
        return cacheMaxAge;
    }

    public static void setCacheMaxAge(int cacheMaxAge) {
        Config.cacheMaxAge = cacheMaxAge;
    }

}
