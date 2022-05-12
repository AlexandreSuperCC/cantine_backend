package fr.utbm.cantine.dao;

import fr.utbm.cantine.model.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserDao extends JpaRepository<UserDomain,Integer> {

    /**
    * @DESC get userinfo according to username and password
    * @data 12/05/2022 19:36
    * @author yuan.cao@utbm.fr
    **/
    @Query(nativeQuery=true, value ="select * from user u where u.name = :name and u.password = :password ")
    UserDomain getUserInfoByCond(@Param("name") String username, @Param("password") String password);

}
