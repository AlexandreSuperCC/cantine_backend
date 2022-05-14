package fr.utbm.cantine.controller.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.controller.BaseController;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.UserDomain;
import fr.utbm.cantine.service.index.IUserService;
import fr.utbm.cantine.utils.APIResponse;
import fr.utbm.cantine.utils.CommonUtils;
import fr.utbm.cantine.utils.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
public class AuthController extends BaseController {

    @Autowired
    IUserService userService;



    @PostMapping(value = "/login")
    @ResponseBody
    public APIResponse toLogin(
            HttpServletRequest request,
            @RequestBody UserDomain loginUser//should be an object
    ){
        String ip = CommonUtils.getIpAddrByRequest(request);//gei ip and solve the bug of filtering the cache when logging
        String token;
        try{
            String username= loginUser.getName();
            String password=loginUser.getPassword();
            LOGGER.info("user: ["+username+"] pwd: ["+password+"] arrives");
            UserDomain userInfo = userService.login(username,password);
            //no exception so succeed=>

            token = JwtUtil.sign();
            if(token!=null){
                return APIResponse.success(token,userInfo.getId(),new LoginReturnObj(userInfo.getRole(),username));
            }else{
                return APIResponse.fail(ErrorConstant.Login.MAKE_TOKEN_ERROR);
            }
        }catch (Exception e){
            String msg;
            if(e instanceof BusinessException){
                String errMes = e.getMessage()!=null?e.getMessage():((BusinessException) e).getErrorCode();
                LOGGER.error(ip+" : "+errMes);
                return APIResponse.fail(errMes);
            }else{
                msg = e.getMessage().substring(0,500);
                LOGGER.error(msg,e);
            }
            return APIResponse.fail(msg);
        }
    }

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})//cause jackson serialization error
    class LoginReturnObj {
        final Integer role;
        final String name;

        LoginReturnObj(Integer role,String name){
            this.role = role;
            this.name = name;
        }

        public Integer getRole() {
            return role;
        }

        public String getName() {
            return name;
        }
    }
}
