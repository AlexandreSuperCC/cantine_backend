package fr.utbm.cantine.service.admin.impl;

import fr.utbm.cantine.dao.IPlatDao;
import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.admin.IAdminPlatService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPlatServiceImpl implements IAdminPlatService {
    @Autowired
    IPlatDao iPlatDao;

    @Override
    public int updatePlat(PlatDomain platDomain) {
        Integer id = platDomain.getId();
        String name = platDomain.getName();
        String content = platDomain.getContent();
        String imgurl = platDomain.getImgurl();
        Integer type = platDomain.getType();
        Integer day = platDomain.getDay();
        assert id!=0;
        return iPlatDao.updatePlat(id,name,content, imgurl,type,day);
    }

    @Override
    public int deletePlat(Integer id) {
        assert id!=null;
        return iPlatDao.deletePlat(id);
    }

    @Override
    public int realDeletePlat(Integer id) {
        assert id!=null;
        return iPlatDao.realDeletePlat(id);
    }
    @Override
    public int restorePlat(Integer id) {
        assert id!=null;
        return iPlatDao.restorePlat(id);
    }

    @Override
    public List<PlatDomain> queryAllDeletedPlats(Integer cid) {
        List<PlatDomain> listPlats = iPlatDao.getAllDeletedPlats(cid);
        return listPlats;
    }
}
