//package ee.mihkel.veebipood.util;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class CronJobScheduler {
//
//    // * ---> sekund
//    // * * ---> minut
//    // * * * ---> tund
//    // * * * * ---> kuupäev kuus
//    // * * * * * ---> kuus
//    // * * * * * * ---> nädalapäev kas numbri või MON-SUN   MON-FRI    SAT-SUN
//    // sunday on nii 0 kui 7
//
//    @Scheduled(cron = "* * * * * *")
//    public void printEverySecond() {
//        Date date = new Date();
//        System.out.println(date.getMinutes() + ":" + date.getSeconds());
//    }
//
//    @Scheduled(cron = "*/3 * * * * *")
//    public void printEvery3Seconds() {
//        Date date = new Date();
//        System.out.println("3 sek täis");
//    }
//
//    @Scheduled(cron = "0 * * * * *")
//    public void printEveryMinute() {
//        System.out.println("Minut täis");
//    }
//
//    @Scheduled(cron = "0 0 * * * *")
//    public void printEveryHour() {
//        System.out.println("Tund täis");
//    }
//}
