package fr.utbm.cantine.service.external;

import fr.utbm.cantine.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Type Executor.java
 * @Desc Universal Abstract Class : receive the data from external system
 * @author yuan.cao@utbm.fr
 * @date 30/04/2022 14:43
 * @version 1.0
 */
public abstract class Executor <T>{

    protected static Logger log = LoggerFactory.getLogger(Executor.class);

    /**
     * the list of the objects to execute
     */
    protected List<T> toExecuteList = new ArrayList<>();

    /**
     * thread-safe
     * @param toExecuteObject the object wait to be executed
     */
    public synchronized void addToExecuteList(T toExecuteObject) {
        if (toExecuteObject != null){
            toExecuteList.add(toExecuteObject);
        }
    }

    /**
    * @DESC the unified method to execute, thread-safe
    * @return the result of the execution
    * @data 01/05/2022 12:23
    * @author yuan.cao@utbm.fr
    **/
    public synchronized String exec() throws BusinessException{
        log.info("Now we have "+toExecuteList.size()+" objects to execute");
        for(T object: toExecuteList){
            this.execute(object);
        }
        toExecuteList.clear();
        return "success";
    }

    /**
    * @DESC the core method to execute
    * @param data the text received
    * @return the result of execution
    * @data 30/04/2022 15:00
    * @author yuan.cao@utbm.fr
    **/
    protected abstract String execute(T curObject) throws BusinessException;
}
