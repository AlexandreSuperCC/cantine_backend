package fr.utbm.cantine.service.external;

import com.alibaba.fastjson.JSON;
import fr.utbm.cantine.config.websocket.WebSocketServer;
import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.PeopleCapturerDomain;
import fr.utbm.cantine.model.PlatCapturerDomain;
import fr.utbm.cantine.utils.APIResponse;
import fr.utbm.cantine.utils.security.JwtUtil;

import java.util.concurrent.ConcurrentHashMap;

public class PeopleExecutor extends Executor <PeopleCapturerDomain> {

    private static ConcurrentHashMap<Integer, PeopleCapturerDomain> peopleMap = new ConcurrentHashMap<>();

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
                ||curObject.getTotalIn()==null
                ||curObject.getTotalOut()==null
                ||curObject.getCurTime()==null
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

        Integer cantineID = curObject.getCid();

        if( peopleMap.containsKey(cantineID) ){
            PeopleCapturerDomain oldObject=peopleMap.get(cantineID);
            if(curObject.getTotalOut()-oldObject.getTotalOut()!=0) {
                curObject.setWaitTime(
                        (int) ((double) (curObject.getCurTime() - oldObject.getCurTime()) / (curObject.getTotalOut() - oldObject.getTotalOut()) / 1000 * curObject.getNumberOfPeople())
                );
            }
            else {
                curObject.setWaitTime(
                        (int) ((double)oldObject.getWaitTime()/oldObject.getNumberOfPeople()*curObject.getNumberOfPeople())
                );
            }
            peopleMap.put(cantineID, curObject);
        }
        else {
            peopleMap.put(cantineID,curObject);
        }

        log.info("The Number of people waiting in the canteen ["+cantineID+"] is : "+curObject.getNumberOfPeople()+" , you have to wait ["+curObject.getWaitTime()+"] second , now sending data to client...");
        String jsonString = JSON.toJSONString(APIResponse.success(curObject,"people"));
        WebSocketServer.sendInfo(jsonString, String.valueOf(cantineID));
        /*inform client code using WebSocket*/

        return "success";
    }
}
