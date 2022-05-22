package fr.utbm.cantine.schedule;

import fr.utbm.cantine.constant.CommonConstant;
import fr.utbm.cantine.dao.IPlatDao;
import fr.utbm.cantine.exception.BusinessException;
import fr.utbm.cantine.model.PeopleCapturerDomain;
import fr.utbm.cantine.model.PlatCapturerDomain;
import fr.utbm.cantine.model.PlatDomain;
import fr.utbm.cantine.service.external.Executor;
import fr.utbm.cantine.service.external.PeopleExecutor;
import fr.utbm.cantine.service.external.PlatExecutor;
import fr.utbm.cantine.utils.APIResponse;
import fr.utbm.cantine.utils.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/*
    this class is used to make auto-tasks to send massage to sites.
 */
@Component
public class Scheduler {

    IPlatDao iPlatDao;
    static List<Integer[]> plats=new ArrayList<>();

    static Integer totalIn=0;
    static Integer totalOut=0;
    static Random random=new Random();
    protected static Logger log = LoggerFactory.getLogger(Executor.class);

    @Autowired
    public Scheduler(IPlatDao iPlatDao) {
        this.iPlatDao=iPlatDao;
        List<PlatDomain> list=iPlatDao.getAllActivePlats(1);
        int nowday= Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if(nowday==1){
            nowday=6;
        }
        else {
            nowday-=2;
        }
        for(PlatDomain plat:list){
            if(plat.getDay()==nowday) {
                plats.add(new Integer[]{plat.getId(), 100});
            }
        }
    }

    @Scheduled(cron="*/20 * * * * ?") //auto-execute each 10s
    public void autoPeopleCapturer(){
        totalIn+=random.nextInt(10)+1;
        totalOut+= random.nextInt(totalIn-totalOut)+1;
        PeopleCapturerDomain peopleCapturerDomain = new PeopleCapturerDomain.Builder(1, String.valueOf(totalIn),String.valueOf(totalOut), JwtUtil.signForHardware())
                .name(CommonConstant.Capturer.DEFAULT_PLATCAPTURER_NAME)
                .build();

        Executor<PeopleCapturerDomain> executor = PeopleExecutor.getInstance();
        executor.addToExecuteList(peopleCapturerDomain);
        String res = null;
        try{
            res = executor.exec();
        }catch (BusinessException be){
            log.error("carteen 1:" + APIResponse.fail(be.getErrorCode()));
        }
    }

    @Scheduled(cron="*/10 * * * * ?") //auto-execute each 10s
    public void autoPlatCapturer(){
        if(!plats.isEmpty()){
            int index=random.nextInt(plats.size());
            int id=plats.get(index)[0];
            int weight=plats.get(index)[1]-1;
            plats.set(index,new Integer[]{id,weight});
            if(weight==0){
                plats.remove(index);
            }

            PlatCapturerDomain platCapturerDomain = new PlatCapturerDomain.Builder(id, 1, String.valueOf(weight),JwtUtil.signForHardware())
                    .name(CommonConstant.Capturer.DEFAULT_PLATCAPTURER_NAME)
                    .build();

            Executor<PlatCapturerDomain> executor = PlatExecutor.getInstance();
            executor.addToExecuteList(platCapturerDomain);
            String res = null;
            try{
                res = executor.exec();
            }catch (BusinessException be){
                log.error("Plat:" + APIResponse.fail(be.getErrorCode()));
            }
        }
    }
}

