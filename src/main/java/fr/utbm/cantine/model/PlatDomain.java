package fr.utbm.cantine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * @Type PlatDomain.java
 * @Desc the model for plat
 * @author yuan.cao@utbm.fr
 * @date 26/04/2022 22:35
 * @version 1.0
 */
@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "rate_cast")
    private String rate;

    @Column(name = "content")
    private String content;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "day")
    private Integer day;

    @Column(name = "imgurl")
    private String imgurl;

    @Column(name = "ts")
    private String ts;

    @Column(name = "cid")
    private Integer cid;

    @Column(name ="ctimes")
    private Integer ctimes;

    @Override
    public String toString() {
        return "PlatDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", rate='" + rate + '\'' +
                ", content='" + content + '\'' +
                ", amount=" + amount +
                ", day=" + day +
                ", imgurl='" + imgurl + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
