package fr.utbm.cantine.dao;

import fr.utbm.cantine.model.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserDao extends JpaRepository<UserDomain,Integer> {

    /**
    * @DESC get userinfo according to username and password
    * @data 12/05/2022 19:36
    * @author yuan.cao@utbm.fr
    **/
    @Query(nativeQuery=true, value ="select * from user u where u.name = :name and u.password = :password and u.cid = :cid")
    UserDomain getUserInfoByCond(@Param("name") String username, @Param("password") String password, @Param("cid") Integer cid);

    /**
    * @DESC get all user from this canteen
    * @param
    * @return
    * @data 17/05/2022 12:44
    * @author yuan.cao@utbm.fr
    **/
    @Query(nativeQuery=true, value ="select * from user u where u.cid = :cid")
    List<UserDomain> getAllUser(@Param("cid") Integer cid);



    /**
    * @DESC change the attributes of the user
    * @param
    * @return
    * @data 17/05/2022 13:25
    * @author yuan.cao@utbm.fr
    **/
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="delete from user u where u.id=:id")
    int deleteUser(@Param("id") Integer id);


    /**
    * @DESC before update user password, verify
    * @param
    * @return
    * @data 17/05/2022 13:41
    * @author yuan.cao@utbm.fr
    **/
    @Query(nativeQuery=true, value ="select * from user u where u.id = :id and u.name=:name and u.password=:password ")
    UserDomain verifyBeforeUpdatePwd(@Param("id") Integer id,
                                     @Param("name") String name,
                                     @Param("password") String password);

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update user u " +
            "set u.password = :password " +
            "where u.id=:id")
    void updateUserPwd(@Param("id") Integer id,
                   @Param("password") String password
    );

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update user u " +
            "set u.last_time = now(), u.last_ip = :last_ip " +
            "where u.id=:id")
    void updateLoginInfo(@Param("id") Integer id,
                         @Param("last_ip") String lastIp
    );

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="update user u " +
            "set u.name = :name, " +
            "u.role = :role " +
            "where u.id=:id ")
    void updateUser(@Param("id") Integer id,
                    @Param("name") String name,
                    @Param("role") Integer role
    );

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="insert into user (id,name,password,role,cid) " +
            "values (:id, :name, :password,:role, :cid) ")
    void addUser(@Param("id") Integer id,
                @Param("name") String name,
                @Param("password") String password,
                @Param("role") Integer role,
                @Param("cid") Integer cid
    );
}
