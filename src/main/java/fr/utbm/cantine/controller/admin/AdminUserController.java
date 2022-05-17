package fr.utbm.cantine.controller.admin;

import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.UserDomain;
import fr.utbm.cantine.service.admin.IAdminUserService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminUserController {

    @Autowired
    IAdminUserService iAdminUserService;

    @GetMapping("/getAllUsers")
    public APIResponse<List<UserDomain>> getAllUsers (
            @RequestParam(value = "cid",required = true)
                    Integer cid) {
        List<UserDomain> userDomainList = iAdminUserService.getAllUser(cid);

        return APIResponse.success(userDomainList);
    }

    @PostMapping("/deleteUser")
    public APIResponse deleteUser(
            @RequestParam(value = "id",required = true) Integer id
    ){
        try {
            assert id != null;
            iAdminUserService.deleteUser(id);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    @PostMapping(value = "/updatePwd")
    public APIResponse updatePwd(
            HttpServletRequest request,
            @RequestParam(value = "id",required = true) Integer id,
            @RequestParam(value = "name",required = true) String name,
            @RequestParam(value = "oldPassword",required = true) String oldPassword,
            @RequestParam(value = "newPassword",required = true) String newPassword
    ){
        try {
            assert id!=null;
            iAdminUserService.updateUserPwd(id,name,oldPassword,newPassword);
        }catch (Exception e){
            String msg;
            if(e instanceof BusinessException){
                String errMes = e.getMessage()!=null?e.getMessage():((BusinessException) e).getErrorCode();
                return APIResponse.fail(errMes);
            }else{
                msg = e.getMessage().length()>500
                        ?e.getMessage().substring(0,500)
                        :e.getMessage();
            }
            return APIResponse.fail(msg);
        }
        return APIResponse.success();
    }

    @PostMapping(value = "/updateUser")
    public APIResponse updatePlat(
            HttpServletRequest request,
            @RequestBody UserDomain userDomain//should be an object
    ){
        try {
            assert userDomain!=null;
            iAdminUserService.updateUser(userDomain);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    @PostMapping(value = "/addUser")
    public APIResponse addUser(
            HttpServletRequest request,
            @RequestBody UserDomain userDomain//should be an object
    ){
        try {
            assert userDomain!=null;
            iAdminUserService.addUser(userDomain);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }
}
