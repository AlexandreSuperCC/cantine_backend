package fr.utbm.cantine.controller.test;

import fr.utbm.cantine.controller.BaseController;
import fr.utbm.cantine.service.test.ITestWebSocket;
import fr.utbm.cantine.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/ws/toServer")
public class WebSocketTestController extends BaseController {

    @Autowired
    private ITestWebSocket iTestWebSocket;

    @PostMapping("/pushToWeb")
    @ResponseBody
    public APIResponse pushToWeb(){

        iTestWebSocket.printTime();
        return APIResponse.success();
    }


    @Override
    public APIResponse getBaseData() {
        return null;
    }
}
