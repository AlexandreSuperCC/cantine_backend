package fr.utbm.cantine.controller.index;

import fr.utbm.cantine.controller.BaseController;
import fr.utbm.cantine.model.NewsDomain;
import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.index.INewsService;
import fr.utbm.cantine.service.index.IPlatService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseBody
    @GetMapping("queryAllNews")
    public APIResponse<List<NewsDomain>> getAllNews (
            @RequestParam(value = "cid",required = true)
                    Integer cid) {
        return iNewsService.queryNewsBycid(cid);
    }

    @ResponseBody
    @GetMapping("queryaPlat")
    public List<PlatDomain> getaPlat(
            @RequestParam(value = "id",required = true)
                    Integer id) {
        return iPlatService.queryaPlat(id);
    }

    @GetMapping("updateComment")
    public String updateComment(
            @RequestParam(value = "id",required = true) Integer id,
            @RequestParam(value = "rate",required = true) String rate,
            @RequestParam(value = "ctimes",required = true) Integer ctimes
    ){
        iPlatService.updateComment(id,rate,ctimes);
        return "redirect:/queryaPlat?id="+id;
    }

}
