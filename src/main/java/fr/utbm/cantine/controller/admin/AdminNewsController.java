package fr.utbm.cantine.controller.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.utbm.cantine.model.NewsDomain;
import fr.utbm.cantine.service.admin.IAdminNewsService;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/admin")
public class AdminNewsController {

    @Autowired
    IAdminNewsService iAdminNewsService;

    @PostMapping("/deleteNews")
    public APIResponse deleteNews(
            @RequestBody String params
    ){
        try {
            assert params != null;
            String[] arrStr = getStrFromAPI(params,"selectedArr","idArr");
            if(arrStr!=null&&arrStr.length>0){
                iAdminNewsService.deleteSomeNews(arrStr);
            }
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }


    @PostMapping(value = "/updateNews")
    public APIResponse updatePlat(
            HttpServletRequest request,
            @RequestBody NewsDomain newsDomain//should be an object
    ){
        try {
            assert newsDomain!=null;
            iAdminNewsService.updateNews(newsDomain);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    @PostMapping(value = "/addNews")
    public APIResponse addPlat(
            HttpServletRequest request,
            @RequestBody NewsDomain newsDomain//should be an object
    ){
        try {
            assert newsDomain!=null;
            iAdminNewsService.addNews(newsDomain);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    /**
    * @DESC
     *      * parse the incoming json str and make it to an array with the internal data
     *      example:
     *      {
     *          "selectedArr": {
     *          "idArr": [3, 1]
     *         }
     *      }
    * @param jsonStr the incoming json str, like the example
    * @param attr the name of the internal attribute
    * @return a table of the string
    * @data 16/05/2022 23:06
    * @author yuan.cao@utbm.fr
    **/
    private static String[] getStrFromAPI(String jsonStr, String... attr){
        if(jsonStr==null||"".equals(jsonStr)){
            return null;
        }
        JsonParser jp = new JsonParser();
        //将json字符串转化成json对象
        JsonObject jo = jp.parse(jsonStr).getAsJsonObject();
        //获取message对应的值
        JsonObject jsonObject = jo.get(attr[0]).getAsJsonObject();
        if(jsonObject==null) return null;
        String arrStr = jsonObject.get(attr[1]).toString();
        if(arrStr==null||arrStr.length()<3) return null;
        String [] strArr = arrStr.substring(1,arrStr.length()-1).split(","); //注意分隔符是需要转译滴...
        return strArr;
    }

}
