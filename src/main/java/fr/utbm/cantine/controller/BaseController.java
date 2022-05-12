package fr.utbm.cantine.controller;

import fr.utbm.cantine.utils.APIResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
/**
 * @Type BaseController.java
 * @Desc the abstract class for all controller
 * @author yuan.cao@utbm.fr
 * @date 26/04/2022 12:13
 * @version 1.0
 */
@Controller
public abstract class BaseController {
    protected static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    /**
    * @DESC can be overwrite or not by the subclass
    * @return
    * @data 09/05/2022 10:40
    * @author yuan.cao@utbm.fr
    **/
     public APIResponse getBaseData(){
         return null;
     }
}
