package fr.utbm.cantine.service.index;

import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.utils.APIResponse;

/**
 * @Type IPlatService.java
 * @Desc interface for the plats in the index page
 * @author yuan.cao@utbm.fr
 * @date 26/04/2022 20:27
 * @version 1.0
 */
public interface IPlatService {
    /**
    * @DESC get all plats' information
    * @return the response of the api
    * @data 26/04/2022 20:28
    * @author yuan.cao@utbm.fr
    **/
    APIResponse queryAllPlats(Integer cid);

    PlatDomain queryaPlat(Integer id);

    public void updateComment(Integer id,String rate,Integer ctimes);
}
