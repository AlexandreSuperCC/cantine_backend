package fr.utbm.cantine.dao;

import fr.utbm.cantine.model.NewsDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface INewsDao extends JpaRepository<NewsDomain,Integer> {

}
