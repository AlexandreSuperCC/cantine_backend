package fr.utbm.cantine.controller.admin;

import fr.utbm.cantine.api.QiniuCloudService;
import fr.utbm.cantine.constant.CommonConstant;
import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.admin.IAdminPlatService;
import fr.utbm.cantine.utils.APIResponse;
import fr.utbm.cantine.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminPlatController {
    @Autowired
    IAdminPlatService iAdminPlatService;

    @PostMapping(value = "/updatePlat")
    public APIResponse updatePlat(
            HttpServletRequest request,
            @RequestBody PlatDomain platDomain//should be an object
    ){
        try {
            assert platDomain!=null;
            iAdminPlatService.updatePlat(platDomain);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    @PostMapping("/deletePlat")
    public APIResponse deletePlat(
            @RequestParam(value = "id",required = true) Integer id
    ){
        try {
            assert id != null;
            iAdminPlatService.deletePlat(id);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }
    @PostMapping("/realDeletePlat")
    public APIResponse RealDeletePlat(
            @RequestParam(value = "id",required = true) Integer id
    ){
        try {
            assert id != null;
            iAdminPlatService.realDeletePlat(id);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }
    @PostMapping("/restorePlat")
    public APIResponse restorePlat(
            @RequestParam(value = "id",required = true) Integer id
    ){
        try {
            assert id != null;
            iAdminPlatService.restorePlat(id);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    @ResponseBody
    @GetMapping("queryAllDeletePlats")
    public APIResponse<List<PlatDomain>> getAllPlats(
            @RequestParam(value = "cid",required = true)
                    Integer cid){
        {
            List<PlatDomain> listPlats = null;
            try {
                assert cid != null;
                listPlats = iAdminPlatService.queryAllDeletedPlats(cid);
            }catch (Exception e){
                return APIResponse.fail(e.getMessage());
            }
            return APIResponse.success(listPlats);
        }
    }

    @Autowired
    QiniuCloudService qiniuCloudService;

    /**
    * @DESC CORE CODE : upload file, using qiniuyun cloud service
    * @param
    * @return
    * @data 16/05/2022 09:46
    * @author yuan.cao@utbm.fr
    **/
    @PostMapping(value = "uploadFile")
    @ResponseBody
    public APIResponse fileUploadToCloud(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestParam(name="file",required = true)
                                                 MultipartFile[] files){
        List<String> returnUploadUrl = new ArrayList<>();
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            for (MultipartFile file : files) {
                String[] fileInfo = CommonUtils.getFileKey(file.getOriginalFilename());
                String fileName = CommonConstant.UploadFile.PREFIX_DIRECTORY_UPLOAD + fileInfo[0].replaceFirst("/", "");
                String url = qiniuCloudService.upload(file, fileName);//upload file on qiniuCloud
                returnUploadUrl.add(CommonConstant.UploadFile.PREFIX_HTTP_UPLOAD+ url);
            }
            return APIResponse.success(returnUploadUrl);
        }  catch (Exception e) {
            return APIResponse.fail(ErrorConstant.Atth.UPLOAD_FILE_FAIL,e.getMessage());
        }
    }

    @PostMapping(value = "/addPlat")
    public APIResponse addPlat(
            HttpServletRequest request,
            @RequestBody PlatDomain platDomain//should be an object
    ){
        try {
            assert platDomain!=null;
            iAdminPlatService.addPlat(platDomain);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }
}
