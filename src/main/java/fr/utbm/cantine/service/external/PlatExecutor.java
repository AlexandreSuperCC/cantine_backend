package fr.utbm.cantine.service.external;


import com.alibaba.fastjson.JSON;
import fr.utbm.cantine.config.websocket.WebSocketServer;
import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.dao.IPlatDao;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.PlatCapturerDomain;
import fr.utbm.cantine.utils.APIResponse;
import fr.utbm.cantine.utils.CommonUtils;
import fr.utbm.cantine.utils.SpringUtil;
import fr.utbm.cantine.utils.security.JwtUtil;

import java.util.concurrent.ConcurrentHashMap;
/**
 * @Type PlatExecutor.java
 * @Desc unique executor for plat, use singleton, safe for use by multiple threads
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 12:55
 * @version 1.0
 */
public class PlatExecutor extends Executor <PlatCapturerDomain>{

    /**
     * the id of the plat, the weight of each plat estimated
     */
    private static ConcurrentHashMap<Integer, Double> platWeightMap = new ConcurrentHashMap<>();

    /**
     * allow synchronization and uniqueness for multiple threads
     */
    private volatile static PlatExecutor platExecutor;

    private PlatExecutor(){}

    /**
    * @DESC to get unique platExecutor, use Singleton, safe for use by multiple threads
    * @return unique platExecutor
    * @data 01/05/2022 12:56
    * @author yuan.cao@utbm.fr
    **/
    public static PlatExecutor getInstance(){
        if(platExecutor==null){
            synchronized (PlatExecutor.class){
                if(platExecutor==null){
                    platExecutor = new PlatExecutor();
                }
            }
        }
        return platExecutor;
    }

    /**
     * @DESC core code, first we get the id of the plat and current plat weight
     * second we calculate the change of each plat and update the weight of each plat
     * then we send the estimated the number of the rest for each plat using WebSocket
    * @param
    * @return curObject : the current weight of the plat
    * @data 01/05/2022 12:47
    * @author yuan.cao@utbm.fr
    **/
    @Override
    protected String execute(PlatCapturerDomain curObject) throws BusinessException{
        Boolean changeable = false;//flag to determine if it should be sent to client and update in database
        Integer rpnInt = 9999;//the rest number of the plat
        if (curObject==null
                ||curObject.getPid()==null
                ||curObject.getWeightOrNumber()==null
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

        /*core weight calculation code*/
        Double curWeight = Double.parseDouble(curObject.getWeightOrNumber());
        Integer curPlatId = curObject.getPid();
        if (platWeightMap.containsKey(curPlatId)){//old plat
            Double lastWeight = platWeightMap.get(curPlatId);
            Double curDifference = lastWeight-curWeight;
            if(curDifference<=0){
                changeable=false;
            }else {
                Double restPlatNumber = curWeight/curDifference;
                rpnInt = CommonUtils.doubleToInteger(restPlatNumber);
                curObject.setWeightOrNumber(String.valueOf(rpnInt));
                platWeightMap.put(curPlatId,curWeight);
                changeable = true;
            }
        }else{//new plat
            platWeightMap.put(curPlatId, curWeight);
            changeable = false;
        }
        /*core weight calculation code*/

        String cantineID = String.valueOf(curObject.getCid());
        if (changeable){
            /*update data in database*/
            IPlatDao iPlatDao = SpringUtil.getBean(IPlatDao.class);
            Integer res = iPlatDao.updateAmount(curPlatId,rpnInt);
            if (res!=1) return "fail";
            /*update data in database*/
            log.info("The current rest amount of the plat ["+curPlatId+"] in the canteen ["+cantineID+"] is : "+rpnInt+" , now sending data to client...");

            /*inform client code using WebSocket*/
            String jsonString = JSON.toJSONString(APIResponse.success(curObject,"plat"));//use msg="plat" here to precise this is the plat info
            WebSocketServer.sendInfo(jsonString,cantineID);
            /*inform client code using WebSocket*/

        }


        return "success";
    }
}
