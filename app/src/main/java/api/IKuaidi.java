package api;

import api.model.Kuaidi;

/**
 * Created by yangshiyou on 2016/12/20.
 */

public interface IKuaidi {

    /**
     * 快递接口
     * http://www.kuaidi100.com/query?type=快递公司代号&postid=快递单号
     * ps:快递公司编码:申通="shentong" EMS="ems" 顺丰="shunfeng" 圆通="yuantong" 中通="zhongtong" 韵达="yunda" 天天="tiantian" 汇通="huitongkuaidi" 全峰="quanfengkuaidi" 德邦="debangwuliu" 宅急送="zhaijisong"
     *
     * @param type        快递公司代号
     * @param postid      快递单号
     * @param apiCallBack
     */
    public void query(String type, String postid, ApiCallBack apiCallBack);

}
