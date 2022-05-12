package fr.utbm.cantine.service.index.impl;

import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.dao.IUserDao;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.UserDomain;
import fr.utbm.cantine.service.index.IUserService;
import fr.utbm.cantine.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    protected static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);


    @Autowired
    private IUserDao userDao;

    @Override
    public UserDomain login(String username, String password) {
        if(StringUtils.isBlank(username)){
            throw BusinessException.withErrorCode(ErrorConstant.Login.LOGIN_USERNAME_EMPTY);
        }
        String pwd = CommonUtils.MD5encode(username+password);
        LOGGER.info("Encrypted pwd : "+pwd);

        UserDomain user = userDao.getUserInfoByCond(username,pwd);
        if(user==null){
            throw BusinessException.withErrorCode(ErrorConstant.Login.USERNAME_PASSWORD_ERROR);
        }

        return user;
    }
}
