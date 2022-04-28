package fr.utbm.cantine.service.impl;

import fr.utbm.cantine.dao.INewsDao;
import fr.utbm.cantine.model.NewsDomain;
import fr.utbm.cantine.service.INewsService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    INewsDao iNewsDao;

    @Override
    public APIResponse queryAllNews(){
        List<NewsDomain> listNews=iNewsDao.findAll();
        return APIResponse.success(listNews);
    }

}
