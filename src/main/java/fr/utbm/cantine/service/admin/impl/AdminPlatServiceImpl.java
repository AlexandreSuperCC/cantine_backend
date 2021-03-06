package fr.utbm.cantine.service.admin.impl;

import fr.utbm.cantine.dao.IPlatDao;
import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.admin.IAdminPlatService;
import fr.utbm.cantine.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public int addPlat(PlatDomain platDomain) {
        Integer id = CommonUtils.getRandomID();
        String name = platDomain.getName();
        String content = platDomain.getContent();
        String imgurl = platDomain.getImgurl();
        Integer type = platDomain.getType();
        Integer day = platDomain.getDay();
        Integer cid = platDomain.getCid();
        assert id!=0;
        return iPlatDao.addPlat(id,name,type,content,day,imgurl,cid);
    }

    @Override
    public List<PlatDomain> getLeastNumPlatsNow(Integer cid) {
        assert cid!=null;
        Integer curDay = CommonUtils.getCurWeek();
        List<PlatDomain>  resList=null;
        if(curDay!=-1){
            resList= iPlatDao.getLeastNumPlatsNow(cid,curDay);
        }
        return resList==null
                ?new ArrayList<>()
                :resList;
    }
}
