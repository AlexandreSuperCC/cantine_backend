package fr.utbm.cantine.model;

/**
 * @Type BaseCapturerDomain.java
 * @Desc the info of the capturer, used to define the data sending from the capturer
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 12:28
 * @version 1.0
 */
public class BaseCapturerDomain {
    /**
     * the id of the capturer
     */
    protected Integer id;
    /**
     * the name of the capturer
     */
    protected String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
