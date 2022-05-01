package fr.utbm.cantine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Type PlatCapturerDomain.java
 * @Desc the plat data capturer, using builder pattern
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 13:17
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatCapturerDomain extends BaseCapturerDomain {
    /**
     * the id of the plat
     */
    private Integer pid;
    /**
     * the number of weight received
     */
    private String weight;
    /**
     * the id of the canteen
     */
    private Integer cid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /*builder pattern*/
    public static class Builder{
        //required parameters
        private final Integer pid;
        private final Integer cid;
        private final String weight;

        //optional parameters - initialized to default values
        private String name = "default_capturer";
        private Integer id = -1;

        public Builder(Integer pid,Integer cid,String weight){
            this.pid = pid;
            this.cid = cid;
            this.weight = weight;
        }
        public Builder id(Integer id){this.id=id;return this;}
        public Builder name(String name){this.name=name;return this;}
        public PlatCapturerDomain build(){
            return new PlatCapturerDomain(this);
        }
    }
    private PlatCapturerDomain(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.cid = builder.cid;
        this.pid = builder.pid;
        this.weight = builder.weight;
    }
    /*builder pattern*/

}
