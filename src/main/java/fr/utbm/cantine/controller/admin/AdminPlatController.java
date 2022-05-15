package fr.utbm.cantine.controller.admin;

import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.admin.IAdminPlatService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminPlatController {
    @Autowired
    IAdminPlatService iAdminPlatService;

    @PostMapping(value = "/updatePlat")
    public APIResponse updatePlat(
            HttpServletRequest request,
            @RequestBody PlatDomain platDomain//should be an object
    ){
        try {
            assert platDomain!=null;
            iAdminPlatService.updatePlat(platDomain);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    @PostMapping("/deletePlat")
    public APIResponse deletePlat(
            @RequestParam(value = "id",required = true) Integer id
    ){
        try {
            assert id != null;
            iAdminPlatService.deletePlat(id);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }
    @PostMapping("/realDeletePlat")
    public APIResponse RealDeletePlat(
            @RequestParam(value = "id",required = true) Integer id
    ){
        try {
            assert id != null;
            iAdminPlatService.realDeletePlat(id);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }
    @PostMapping("/restorePlat")
    public APIResponse restorePlat(
            @RequestParam(value = "id",required = true) Integer id
    ){
        try {
            assert id != null;
            iAdminPlatService.restorePlat(id);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    @ResponseBody
    @GetMapping("queryAllDeletePlats")
    public APIResponse<List<PlatDomain>> getAllPlats(
            @RequestParam(value = "cid",required = true)
                    Integer cid){
        {
            List<PlatDomain> listPlats = null;
            try {
                assert cid != null;
                listPlats = iAdminPlatService.queryAllDeletedPlats(cid);
            }catch (Exception e){
                return APIResponse.fail(e.getMessage());
            }
            return APIResponse.success(listPlats);
        }    }
}
