package fr.utbm.cantine.service.admin.impl;

import fr.utbm.cantine.dao.INewsDao;
import fr.utbm.cantine.model.NewsDomain;
import fr.utbm.cantine.service.admin.IAdminNewsService;
import fr.utbm.cantine.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminNewsServiceImpl implements IAdminNewsService {

    @Autowired
    INewsDao iNewsDao;

    @Override
    public void deleteSomeNews(String[] idArr) {
        assert idArr!=null;
        for (String idStr : idArr) {
            iNewsDao.deleteNews(Integer.parseInt(idStr));
        }
    }

    @Override
    public void updateNews(NewsDomain newsDomain) throws Exception{
        Integer id = newsDomain.getId();
        String content = newsDomain.getContent();
        String time = newsDomain.getTime();
        assert id!=0;
        iNewsDao.updateNews(id,content,time);
    }

    @Override
    public void addNews(NewsDomain newsDomain) throws Exception{
        Integer id = CommonUtils.getRandomID();
        String content = newsDomain.getContent();
        String time = newsDomain.getTime();
        Integer cid = newsDomain.getCid();
        assert id!=0;
        iNewsDao.addNews(id,content,time,cid);
    }
}
