package fun.huanghai.listener;

import fun.huanghai.event.TestEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class TestListener implements ApplicationListener {


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if(applicationEvent instanceof TestEvent){
            TestEvent testEvent = (TestEvent) applicationEvent;
            testEvent.print();
        }
    }
}
