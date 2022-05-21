package fr.utbm.cantine.service.admin;

import fr.utbm.cantine.model.CanteenDomain;
import fr.utbm.cantine.model.UserDomain;

import java.util.List;

public interface IAdminUserService {
    List<UserDomain> getAllUser(Integer cid);

    void deleteUser(Integer id);

    void updateUserPwd(Integer id, String name, String oldPassword, String newPassword);

    void addUser(UserDomain userDomain);

    void updateUser(UserDomain userDomain);

    CanteenDomain getCanteenInfo(Integer id);

}