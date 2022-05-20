package fr.utbm.cantine.service.index;

import fr.utbm.cantine.model.UserDomain;

/**
 * @Type IUserService.java
 * @Desc used for traiting data of user
 * @author yuan.cao@utbm.fr
 * @date 12/05/2022 18:49
 * @version 1.0
 */
public interface IUserService {
    /**
    * @DESC method for login
    * @data 12/05/2022 18:49
    * @author yuan.cao@utbm.fr
    **/
    UserDomain login(String username, String password, Integer cid,String ip);
}
