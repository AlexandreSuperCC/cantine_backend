package fr.utbm.cantine.service.admin;

import fr.utbm.cantine.model.PlatDomain;

import java.util.List;

/**
 * @Type IAdminPlatService.java
 * @Desc for dealing with plat data in admin page
 * @author yuan.cao@utbm.fr
 * @date 2022-05-15 22:40:11
 * @version 1.0
 */
public interface IAdminPlatService {
    /**
     * @DESC get all deleted plat (dr=1)
     * @return
     * @date 2022-05-16 00:40:16
     * @author yuan.cao@utbm.fr
     **/
    List<PlatDomain> queryAllDeletedPlats(Integer cid);
    int updatePlat(PlatDomain platDomain);
    int deletePlat(Integer id);
    int realDeletePlat(Integer id);
    int restorePlat(Integer id);
    int addPlat(PlatDomain platDomain);
    List<PlatDomain> getLeastNumPlatsNow(Integer cid);

}
