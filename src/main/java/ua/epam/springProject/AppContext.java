package ua.epam.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AppContext {
    private static AppContext appContext = null;
    private ApplicationContext ctx;

    private AppContext() {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
    }

    static ApplicationContext getInstance(){
        if(appContext == null){
         appContext = new AppContext();
        }
        return appContext.ctx;
    }
}
