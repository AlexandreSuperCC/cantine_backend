package fr.utbm.cantine.service.external;

import com.alibaba.fastjson.JSON;
import fr.utbm.cantine.config.websocket.WebSocketServer;
import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.PeopleCapturerDomain;
import fr.utbm.cantine.model.PlatCapturerDomain;
import fr.utbm.cantine.utils.APIResponse;
import fr.utbm.cantine.utils.security.JwtUtil;

public class PeopleExecutor extends Executor <PeopleCapturerDomain> {

    private volatile static PeopleExecutor peopleExecutor;

    private PeopleExecutor(){}

    public static PeopleExecutor getInstance(){
        if(peopleExecutor==null){
            synchronized (PeopleExecutor.class){
                if(peopleExecutor==null){
                    peopleExecutor = new PeopleExecutor();
                }
            }
        }
        return peopleExecutor;
    }

    @Override
    protected String execute(PeopleCapturerDomain curObject) throws BusinessException {
        if (curObject==null
                ||curObject.getNumberOfPeople()==null
                ||curObject.getCid()==null
                ||curObject.getToken()==null
        ){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        /**
         * for security proposal, each message should be legal
         */
        String token = curObject.getToken();
        assert token!=null;
        if(!JwtUtil.verify(token)){
            throw BusinessException.withErrorCode(ErrorConstant.Security.JWT_UNAUTHORIZED);
        }

        String cantineID = String.valueOf(curObject.getCid());
        log.info("The Number of people waiting in the canteen ["+cantineID+"] is : "+curObject.getNumberOfPeople()+" , you have to wait ["+curObject.getWaitTime()+"] second , now sending data to client...");
        String jsonString = JSON.toJSONString(APIResponse.success(curObject,"people"));
        WebSocketServer.sendInfo(jsonString,cantineID);
        /*inform client code using WebSocket*/

        return "success";
    }
}
