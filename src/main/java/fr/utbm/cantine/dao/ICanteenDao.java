package fr.utbm.cantine.dao;

import fr.utbm.cantine.model.CanteenDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICanteenDao extends JpaRepository<CanteenDomain,Integer> {

    /**
     * @DESC get userinfo according to username and password
     * @data 12/05/2022 19:36
     * @author yuan.cao@utbm.fr
     **/
    @Query(nativeQuery=true, value ="select * from t_cantine c where c.id = :id and c.dr=0 ")
    CanteenDomain getCanteenInfo(@Param("id") Integer id);
}
