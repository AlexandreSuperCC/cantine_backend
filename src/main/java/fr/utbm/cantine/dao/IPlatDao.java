package fr.utbm.cantine.dao;

import fr.utbm.cantine.model.PlatDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Type IPlatDao.java
 * @Desc API to database for the plats in the index page
 * @author yuan.cao@utbm.fr
 * @date 26/04/2022 13:16
 * @version 1.0
 */
public interface IPlatDao extends JpaRepository<PlatDomain,Integer> {

    /**
    * @DESC get all active plats
    * @return the list of plats
    * @data 26/04/2022 22:37
    * @author yuan.cao@utbm.fr
    **/
    @Query(nativeQuery=true, value ="select m.id,m.name,m.type,CAST(m.rate AS DECIMAL(10,2)) rate_cast,m.ctimes,m.content,m.amount,m.day,m.imgurl,m.cid,m.ts from menu m where m.dr=0 and m.cid = :cid order by m.day,m.type,rate_cast desc ")
    List<PlatDomain> getAllActivePlats(@Param(value = "cid") Integer cid);

    @Query(nativeQuery=true, value ="select m.id,m.name,m.type,CAST(m.rate AS DECIMAL(10,2)) rate_cast,m.ctimes,m.content,m.amount,m.day,m.imgurl,m.cid,m.ts from menu m where m.id = :id ")
    PlatDomain getaPlat(@Param(value = "id") Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update menu m set m.rate = :rate,m.ctimes=:ctimes where m.id=:id")
    public int updateComment(@Param("id") Integer id,@Param("rate") String rate,@Param("ctimes") Integer ctimes);

    /**
    * @DESC update the amount of the plat
    * @return the result of update
    * @data 01/05/2022 21:43
    * @author yuan.cao@utbm.fr
    **/
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update menu m set m.amount = :amount where m.id=:id and m.dr != 1")
    int updateAmount(@Param("id") Integer id,@Param("amount") Integer amount);

    /*************************** Admin Page Part ***************************/
    /**
     * @DESC use for admin page, update plat info
     * @return
     * @date 2022-05-15 22:23:28
     * @author yuan.cao@utbm.fr
     **/
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update menu m " +
            "set m.name = :name, " +
            "m.content = :content, " +
            "m.imgurl = :imgurl, " +
            "m.type = :type, " +
            "m.day = :day " +
            "where m.id=:id")
    int updatePlat(@Param("id") Integer id,
                   @Param("name") String name,
                   @Param("content") String content,
                   @Param("imgurl") String imgurl,
                   @Param("type") Integer type,
                   @Param("day") Integer day
    );
    /**
     * @DESC use for admin page, delete plat
     * @return
     * @date 2022-05-15 22:23:28
     * @author yuan.cao@utbm.fr
     **/
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update menu m set m.dr = 1 where m.id=:id")
    int deletePlat(@Param("id") Integer id);

    /**
     * @DESC use for admin page, restore deleted plat
     * @return
     * @date 2022-05-15 22:23:28
     * @author yuan.cao@utbm.fr
     **/
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update menu m set m.dr = 0 where m.id=:id")
    int restorePlat(@Param("id") Integer id);

    /**
     * @DESC use for admin page, get all deleted plat
     * @return
     * @date 2022-05-15 22:35:03
     * @author yuan.cao@utbm.fr
     **/
    @Query(nativeQuery=true, value ="select m.id,m.name,m.type,CAST(m.rate AS DECIMAL(10,2)) rate_cast,m.ctimes,m.content,m.amount,m.day,m.imgurl,m.cid,m.ts from menu m where m.dr = 1 ")
    List<PlatDomain> getDeletedPlat();

    /**
     * @DESC use for admin page, delete plat forever
     * @return
     * @date 2022-05-15 22:35:03
     * @author yuan.cao@utbm.fr
     **/
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="delete from menu m where m.id=:id")
    int realDeletePlat(@Param("id") Integer id);

    /**
     * @DESC get all deleted plats (dr=1)
     * @return
     * @date 2022-05-16 00:38:40
     * @author yuan.cao@utbm.fr
     **/
    @Query(nativeQuery=true, value ="select m.id,m.name,m.type,CAST(m.rate AS DECIMAL(10,2)) rate_cast,m.ctimes,m.content,m.amount,m.day,m.imgurl,m.cid,m.ts from menu m where m.dr=1 and m.cid = :cid order by m.day,m.type,rate_cast desc ")
    List<PlatDomain> getAllDeletedPlats(@Param(value = "cid") Integer cid);
}
