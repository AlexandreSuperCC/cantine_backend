package fr.utbm.cantine.service.admin.impl;

import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.dao.ICanteenDao;
import fr.utbm.cantine.dao.IUserDao;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.CanteenDomain;
import fr.utbm.cantine.model.UserDomain;
import fr.utbm.cantine.service.admin.IAdminUserService;
import fr.utbm.cantine.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Autowired
    IUserDao iUserDao;
    @Autowired
    ICanteenDao iCanteenDao;

    @Override
    public List<UserDomain> getAllUser(Integer cid) {
        assert cid!=null;
        List<UserDomain> userDomainList = iUserDao.getAllUser(cid);
        userDomainList.stream().forEach(e-> e.setPassword(""));//security proposal, don not send back the password
        return userDomainList;
    }

    @Override
    public void deleteUser(Integer id) {
        assert id!=null;
        iUserDao.deleteUser(id);
    }

    /**
    * @DESC verify if the old password is correct before updating new one
    * @param
    * @return
    * @data 17/05/2022 21:56
    * @author yuan.cao@utbm.fr
    **/
    private void verifyBeforeUpdatePwd(Integer id, String name, String password){
        assert id!=null;
        if(StringUtils.isBlank(name)){
            throw BusinessException.withErrorCode(ErrorConstant.Login.LOGIN_USERNAME_EMPTY);
        }
        String pwd = CommonUtils.MD5encode(name+password);
        UserDomain userDomain = iUserDao.verifyBeforeUpdatePwd(id,name,pwd);

        if(userDomain==null){
            throw BusinessException.withErrorCode(ErrorConstant.Login.OLD_PASSWORD_ERROR);
        }
    }

    @Override
    public void updateUserPwd(Integer id, String name, String oldPassword, String newPassword){

        assert id!=null;
        try{
            this.verifyBeforeUpdatePwd(id,name,oldPassword);
        }catch (BusinessException e){
            throw BusinessException.withErrorCode(e.getErrorCode());
        }
        assert name!=null;
        String pwd = CommonUtils.MD5encode(name+newPassword);
        iUserDao.updateUserPwd(id,pwd);
    }

    public void updateUser(UserDomain userDomain) {
        assert userDomain!=null;
        Integer id = userDomain.getId();
        String name = userDomain.getName();
        Integer role = userDomain.getRole();
        iUserDao.updateUser(id,name,role);
    }

    @Override
    public void addUser(UserDomain userDomain){
        Integer id = CommonUtils.getRandomID();
        String name = userDomain.getName();
        String password = userDomain.getPassword();
        Integer role = userDomain.getRole();
        Integer cid = userDomain.getCid();
        if(StringUtils.isBlank(name)){
            throw BusinessException.withErrorCode(ErrorConstant.Login.LOGIN_USERNAME_EMPTY);
        }
        String pwd = CommonUtils.MD5encode(name+password);
        iUserDao.addUser(id,name,pwd,role,cid);
    }

    @Override
    public CanteenDomain getCanteenInfo(Integer id) {
        assert id!=null;
        return iCanteenDao.getCanteenInfo(id);
    }
}
