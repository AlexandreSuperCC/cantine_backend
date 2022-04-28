package fr.utbm.cantine.controller.index;

import fr.utbm.cantine.controller.BaseController;
import fr.utbm.cantine.model.NewsDomain;
import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.INewsService;
import fr.utbm.cantine.service.IPlatService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Type IndexController.java
 * @Desc the controller for the index page
 * @author yuan.cao@utbm.fr
 * @date 26/04/2022 12:26
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @Autowired
    IPlatService iPlatService;

    @Autowired
    INewsService iNewsService;

    @ResponseBody
    @GetMapping("queryAllPlats")
    public APIResponse<List<PlatDomain>> getAllPlats(
            @RequestParam(value = "cid",required = true)
                    Integer cid){
        return iPlatService.queryAllPlats(cid);
    }

    @GetMapping("queryAllNews")
    public APIResponse<List<NewsDomain>> getAllNews () {
        return iNewsService.queryAllNews();
    }

    @Override
    public APIResponse getBaseData() {
        return null;
    }
}
