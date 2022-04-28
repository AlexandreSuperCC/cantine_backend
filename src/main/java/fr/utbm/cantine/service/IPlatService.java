package fr.utbm.cantine.service;

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
}
