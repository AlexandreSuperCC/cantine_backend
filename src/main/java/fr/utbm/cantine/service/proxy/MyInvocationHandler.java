package fr.utbm.cantine.service.proxy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Type MyInvocationHandler.java
 * @Desc reflexion handler
 * how to use :
 *  IPlatService iPlatService = new PlatServiceImpl();
 *  MyInvocationHandler handler = new MyInvocationHandler(iPlatService);
 *  IPlatService proxy = (IPlatService) handler.getProxy();
 *  proxy.updateAmount(curPlatId,newWeight);
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 22:55
 * @version 1.0
 */
public class MyInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = LogManager.getLogger(MyInvocationHandler .class);

    private Object target;
    public MyInvocationHandler(Object target){
        this.target = target;
    }
    /**
    * @DESC invoke methode
    * @param proxy the object the underlying method is invoked from
    * @param method the method to be called
    * @param the parameters in the method
    * @data 01/05/2022 22:55
    * @author yuan.cao@utbm.fr
    **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target,args);
        LOGGER.info("invoke: "+method+" =====");
        return result;
    }


 /**
 * @DESC get proxy
 * @data 01/05/2022 23:11
 * @author yuan.cao@utbm.fr
 **/
    public Object getProxy() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] clazzInterface = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, clazzInterface, this);
    }


}
