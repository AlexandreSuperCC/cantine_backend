package fr.utbm.cantine.service.admin;

import fr.utbm.cantine.model.NewsDomain;

public interface IAdminNewsService {
    void deleteSomeNews(String[] idArr);
    void updateNews(NewsDomain newsDomain) throws Exception;
    void addNews(NewsDomain newsDomain) throws Exception;
}
