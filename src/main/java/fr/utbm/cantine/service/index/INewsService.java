package fr.utbm.cantine.service.index;

import fr.utbm.cantine.utils.APIResponse;

public interface INewsService {

    APIResponse queryNewsBycid(Integer cid);

}
