package fr.utbm.cantine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleCapturerDomain extends BaseCapturerDomain {

    private Integer NumberOfPeople;

    private Integer totalIn;

    private Integer totalOut;

    /**
     * the id of the canteen
     */
    private Integer cid;

    private Integer waitTime;

    private Long curTime;

    public Integer getNumberOfPeople() {
        return NumberOfPeople;
    }

    public void setNumberOfPeople(Integer NumberOfPeople) {
        this.NumberOfPeople = NumberOfPeople;
    }

    public Integer getTotalIn() {return totalIn; }

    public void setTotalIn(Integer totalIn) { this.totalIn=totalIn; }

    public Integer getTotalOut() {return totalOut; }

    public void setTotalOut(Integer totalOut) { this.totalOut=totalOut; }

    public Integer getCid() { return cid; }

    public void setCid(Integer cid) { this.cid = cid; }

    public Integer getWaitTime() { return waitTime; }

    public void setWaitTime(Integer waitTime) { this.waitTime = waitTime; }

    public Long getCurTime() { return curTime; }

    public void setCurTime(Long curTime) { this.curTime = curTime; }

    /*builder pattern*/
    public static class Builder{
        //required parameters
        private final Integer cid;
        private final Integer NumberOfPeople;
        private final Integer totalIn;
        private final Integer totalOut;
        private final String token;
        private final Integer waitTime;
        private final Long curTime;

        //optional parameters - initialized to default values
        private String name = "default_capturer";
        private Integer id = -1;

        public Builder(Integer cid,String totalIn,String totalOut,String token){
            this.cid = cid;
            this.totalIn=Integer.parseInt(totalIn);
            this.totalOut=Integer.parseInt(totalOut);
            this.NumberOfPeople=this.totalIn-this.totalOut;
            this.token = token;
            this.waitTime=this.NumberOfPeople*8;
            this.curTime=System.currentTimeMillis();
        }
        public PeopleCapturerDomain.Builder id(Integer id){this.id=id;return this;}
        public PeopleCapturerDomain.Builder name(String name){this.name=name;return this;}
        public PeopleCapturerDomain build(){
            return new PeopleCapturerDomain(this);
        }
    }
    private PeopleCapturerDomain(PeopleCapturerDomain.Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.cid = builder.cid;
        this.NumberOfPeople = builder.NumberOfPeople;
        this.totalIn=builder.totalIn;
        this.totalOut=builder.totalOut;
        this.token = builder.token;
        this.waitTime=builder.waitTime;
        this.curTime= builder.curTime;
    }
    /*builder pattern*/
}
