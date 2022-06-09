package fr.utbm.cantine.controller.index;

import fr.utbm.cantine.controller.BaseController;
import fr.utbm.cantine.model.NewsDomain;
import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.index.INewsService;
import fr.utbm.cantine.service.index.IPlatService;
import fr.utbm.cantine.utils.APIResponse;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("queryAllPlats")
    public APIResponse<List<PlatDomain>> getAllPlats(
            @RequestParam(value = "cid",required = true)
                    Integer cid){
        return iPlatService.queryAllPlats(cid);
    }

    @GetMapping("queryAllNews")
    public APIResponse<List<NewsDomain>> getAllNews (
            @RequestParam(value = "cid",required = true)
                    Integer cid) {
        return iNewsService.queryNewsBycid(cid);
    }

    @GetMapping("queryaPlat")
    public PlatDomain getaPlat(
            @RequestParam(value = "id",required = true)
                    Integer id) {
        return iPlatService.queryaPlat(id);
    }

    @PostMapping("updateComment")
    public void updateComment(
            @RequestParam(value = "id",required = true) Integer id,
            @RequestParam(value = "rate",required = true) String rate
    ){

        PlatDomain cur=iPlatService.queryaPlat(id);
        Double oldRate;
        if(cur.getRate()==null){
            oldRate=0d;
        }
        else {
            oldRate=Double.parseDouble(cur.getRate());
        }
        Integer oldCtimes=cur.getCtimes();
        Integer ctimes=oldCtimes+1;
        Double newRate=(oldRate*oldCtimes+Double.parseDouble(rate))/ctimes;
        newRate=(double) Math.round(newRate*10000)/10000;
        rate=String.valueOf(newRate);

        HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
        Session session = hEntityManager.getSession();
        session.clear();

        iPlatService.updateComment(id,rate,ctimes);

    }



}
