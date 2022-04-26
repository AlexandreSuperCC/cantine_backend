package fr.utbm.cantine.service.impl;

import fr.utbm.cantine.dao.IPlatDao;
import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.IPlatService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlatServiceImpl implements IPlatService {
    @Autowired
    IPlatDao iPlatDao;

    @Override
    public APIResponse queryAllPlats() {
        List<PlatDomain> listPlats = iPlatDao.getAllActivePlats();
        return APIResponse.success(listPlats);
    }


}
