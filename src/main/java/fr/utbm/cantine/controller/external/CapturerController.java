package fr.utbm.cantine.controller.external;

import fr.utbm.cantine.constant.CommonConstant;
import fr.utbm.cantine.controller.BaseController;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.PeopleCapturerDomain;
import fr.utbm.cantine.model.PlatCapturerDomain;
import fr.utbm.cantine.service.external.Executor;
import fr.utbm.cantine.service.external.PeopleExecutor;
import fr.utbm.cantine.service.external.PlatExecutor;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Type CapturerController.java
 * @Desc core class : used for receiving the data from capturer
 * @author yuan.cao@utbm.fr
 * @date 30/04/2022 15:14
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/external")
public class CapturerController extends BaseController {



    @GetMapping(value = "fromCapturer")
    public APIResponse receiveFromCapturer(@RequestParam(value = "pid",required = true)Integer pid,
                                           @RequestParam(value = "cid",required = true) Integer cid,
                                           @RequestParam(value = "weight",required = true) String weight,
                                           @RequestParam(value = "token",required = true) String token
                                           ){
        PlatCapturerDomain platCapturerDomain = new PlatCapturerDomain.Builder(pid, cid, weight,token)
                .name(CommonConstant.Capturer.DEFAULT_PLATCAPTURER_NAME)
                .build();

        Executor<PlatCapturerDomain> executor = PlatExecutor.getInstance();
        executor.addToExecuteList(platCapturerDomain);
        String res = null;
        try{
            res = executor.exec();
        }catch (BusinessException be){
            return APIResponse.fail(be.getErrorCode());
        }
        return APIResponse.success(res);
    }

    @GetMapping(value = "fromPeopleCapturer")
    public APIResponse receiveFromPeopleCapturer(@RequestParam(value = "cid",required = true) Integer cid,
                                           @RequestParam(value = "number",required = true) String number,
                                           @RequestParam(value = "token",required = true) String token
    ){
        PeopleCapturerDomain peopleCapturerDomain = new PeopleCapturerDomain.Builder(cid, number,token)
                .name(CommonConstant.Capturer.DEFAULT_PLATCAPTURER_NAME)
                .build();

        Executor<PeopleCapturerDomain> executor = PeopleExecutor.getInstance();
        executor.addToExecuteList(peopleCapturerDomain);
        String res = null;
        try{
            res = executor.exec();
        }catch (BusinessException be){
            return APIResponse.fail(be.getErrorCode());
        }
        return APIResponse.success(res);
    }
}
