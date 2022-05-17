package fr.utbm.cantine.dao;

import fr.utbm.cantine.model.NewsDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface INewsDao extends JpaRepository<NewsDomain,Integer> {

    @Query(nativeQuery=true, value ="select * from news n where n.cid = :cid order by n.time desc ")
    List<NewsDomain> getAllNewsBycid(@Param(value = "cid") Integer cid);

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="delete from news n where n.id=:id")
    int deleteNews(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update news n " +
            "set n.content = :content, " +
            "n.time = :time " +
            "where n.id=:id")
    int updateNews(@Param("id") Integer id,
                   @Param("content") String content,
                   @Param("time") String time
    );

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="insert into news (id,content,time,cid) " +
            "values (:id, :content, :time, :cid) ")
    int addNews(@Param("id") Integer id,
                @Param("content") String content,
                @Param("time") String time,
                @Param("cid") Integer cid
    );
}
