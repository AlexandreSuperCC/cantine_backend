package fr.utbm.cantine.interceptor;

import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.service.index.IUserService;
import fr.utbm.cantine.utils.CommonUtils;
import fr.utbm.cantine.utils.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    @Autowired
    IUserService userService;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        LOGGER.info("UserAgent: {}",request.getHeader(USER_AGENT));
        LOGGER.info("User access address: {}, coming address: {}",url, CommonUtils.getIpAddrByRequest(request));

        // If it is not mapped to the method directly through, you can access the resource.
        // if the action is uploading file, it will not come through the front-end interceptors!!!, so let it pass
        if(!url.startsWith("/admin")
                ||(!(handler instanceof HandlerMethod)
        )){
            return true;
        }

        // is null => return un error
//        String token = request.getHeader("token");
        String objStr = request.getHeader("Authorization");
        if(objStr==null){
            return false;
        }
        String token = CommonUtils.getTokenStr(objStr);

        if(null==token||"".equals(token.trim())){
            response.sendError(ErrorConstant.HttpStatus.NO_TOKEN);
            return false;
        }

//        LOGGER.info("Here is your token: "+token+", and now we do the verification...");
        Boolean result = JwtUtil.verify(token);
        if(result){
            return true;
        }else{
            response.sendError(ErrorConstant.HttpStatus.JWT_UNAUTHORIZED);
            return false;
        }
    }
}
