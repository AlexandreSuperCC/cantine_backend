package fr.utbm.cantine.service.index.impl;

import fr.utbm.cantine.dao.INewsDao;
import fr.utbm.cantine.model.NewsDomain;
import fr.utbm.cantine.service.index.INewsService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    INewsDao iNewsDao;

    @Override
    public APIResponse queryNewsBycid(Integer cid){
        List<NewsDomain> listNews=iNewsDao.getAllNewsBycid(cid);
        return APIResponse.success(listNews);
    }

}
