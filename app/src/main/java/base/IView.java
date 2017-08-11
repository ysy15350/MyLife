package base;

/**
 * Created by yangshiyou on 2016/11/29.
 */

public interface IView {

    /**
     * 初始化View
     */
    public void initView();

    /**
     * 读取缓存
     */
    public void readCahce();

    /**
     * 加载数据
     */
    public void loadData();

    /**
     * 绑定数据
     */
    public void bindData();
}
