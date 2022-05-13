package fr.utbm.cantine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleCapturerDomain extends BaseCapturerDomain {

    private Integer NumberOfPeople;

    /**
     * the id of the canteen
     */
    private Integer cid;

    private Integer waitTime;

    public Integer getNumberOfPeople() {
        return NumberOfPeople;
    }

    public void setNumberOfPeople(Integer NumberOfPeople) {
        this.NumberOfPeople = NumberOfPeople;
    }

    public Integer getCid() { return cid; }

    public void setCid(Integer cid) { this.cid = cid; }

    public Integer getWaitTime() { return waitTime; }

    public void setWaitTime(Integer waitTime) { this.waitTime = waitTime; }

    /*builder pattern*/
    public static class Builder{
        //required parameters
        private final Integer cid;
        private final Integer NumberOfPeople;
        private final String token;
        private final Integer waitTime;

        //optional parameters - initialized to default values
        private String name = "default_capturer";
        private Integer id = -1;

        public Builder(Integer cid,String NumberOfPeople,String token){
            this.cid = cid;
            this.NumberOfPeople = Integer.parseInt(NumberOfPeople);
            this.token = token;
            this.waitTime=this.NumberOfPeople*8;
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
        this.token = builder.token;
        this.waitTime=builder.waitTime;
    }
    /*builder pattern*/
}
