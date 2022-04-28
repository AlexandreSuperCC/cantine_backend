package fr.utbm.cantine.dao;

import fr.utbm.cantine.model.NewsDomain;
import fr.utbm.cantine.model.PlatDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface INewsDao extends JpaRepository<NewsDomain,Integer> {

    @Query(nativeQuery=true, value ="select * from news n where n.cid = :cid order by n.time desc ")
    List<NewsDomain> getAllNewsBycid(@Param(value = "cid") Integer cid);
}
