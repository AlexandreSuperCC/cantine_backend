package fr.utbm.cantine.service.external;


import com.alibaba.fastjson.JSON;
import fr.utbm.cantine.config.websocket.WebSocketServer;
import fr.utbm.cantine.constant.CommonConstant;
import fr.utbm.cantine.constant.ErrorConstant;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.PlatCapturerDomain;
import fr.utbm.cantine.utils.APIResponse;

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
    private static ConcurrentHashMap<Integer, String> platWeightMap = new ConcurrentHashMap<>();

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
    * @return
    * @data 01/05/2022 12:47
    * @author yuan.cao@utbm.fr
    **/
    @Override
    protected String execute(PlatCapturerDomain curObject) {
        if (curObject==null
                ||curObject.getPid()==null
                ||curObject.getWeight()==null
                ||curObject.getCid()==null
        ){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        /*core weight calculation code*/
        Double curWeight = Double.parseDouble(curObject.getWeight());
        Integer curPlatId = curObject.getPid();
        if (platWeightMap.containsKey(curPlatId)){//old plat
            Double lastWeight = Double.parseDouble(platWeightMap.get(curPlatId));
            Double curDifference = lastWeight-curWeight;
            if (Math.abs(curDifference)  <= CommonConstant.Calculation.DEGREE_ACCURACY*lastWeight){
                String newWeight = String.valueOf ((lastWeight+curWeight)/2);
                platWeightMap.put(curPlatId,newWeight);
                curObject.setWeight(newWeight);
            }else{
                curObject.setWeight(String.valueOf(lastWeight));
            }
        }else{//new plat
            platWeightMap.put(curPlatId, String.valueOf(curWeight));
        }
        /*core weight calculation code*/

        String cantineID = String.valueOf(curObject.getCid());
        log.info("The current weight for each plat ["+curPlatId+"] in the canteen ["+cantineID+"] is : "+curWeight+" , now sending data to client...");

        /*inform client code using WebSocket*/
        String jsonString = JSON.toJSONString(APIResponse.success(curObject));
        WebSocketServer.sendInfo(jsonString,cantineID);
        /*inform client code using WebSocket*/

        return "success";
    }
}
