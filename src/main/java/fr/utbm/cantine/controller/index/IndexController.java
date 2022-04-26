package fr.utbm.cantine.controller.index;

import fr.utbm.cantine.controller.BaseController;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Type IndexController.java
 * @Desc the controller for the index page
 * @author yuan.cao@utbm.fr
 * @date 26/04/2022 12:26
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {





    @Override
    public APIResponse getBaseData() {
        return null;
    }
}
