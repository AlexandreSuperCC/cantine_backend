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
    List<PlatDomain> getaPlat(@Param(value = "id") Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update menu m set m.rate = :rate,m.ctimes=:ctimes where m.id=:id")
    public int updateComment(@Param("id") Integer id,@Param("rate") String rate,@Param("ctimes") Integer ctimes);
}
