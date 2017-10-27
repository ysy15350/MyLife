/**
  * Copyright 2016 bejson.com 
  */
package api.model;
import java.util.List;

/**
 * Auto-generated: 2016-12-20 19:56:26
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Kuaidi {

    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<Data> data;
    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setNu(String nu) {
         this.nu = nu;
     }
     public String getNu() {
         return nu;
     }

    public void setIscheck(String ischeck) {
         this.ischeck = ischeck;
     }
     public String getIscheck() {
         return ischeck;
     }

    public void setCondition(String condition) {
         this.condition = condition;
     }
     public String getCondition() {
         return condition;
     }

    public void setCom(String com) {
         this.com = com;
     }
     public String getCom() {
         return com;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setState(String state) {
         this.state = state;
     }
     public String getState() {
         return state;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

}