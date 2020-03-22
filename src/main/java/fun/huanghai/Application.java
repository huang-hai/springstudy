package fun.huanghai;

import fun.huanghai.bean.User;
import fun.huanghai.config.MyAware;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args){
        ApplicationContext application = new ClassPathXmlApplicationContext("classpath:spring.xml");

//        User user = (User) application.getBean("user");
//        User huang = (User) application.getBean("huang");
//        User hai = (User) application.getBean("hai");
//        System.out.println(user==hai);
//        User user = (User) application.getBean("testbean");
//        System.out.println(user);

        //Object car = application.getBean("car");
        //获取carFactoryBean本身 加&前缀
//        Object car = application.getBean("&car");
//        System.out.println(car);

        MyAware myAware = (MyAware) application.getBean("myAware");
        myAware.testAware();
    }

    @Test(expected = BeanCurrentlyInCreationException.class)
    public void testCircleByConstructor() throws Throwable {
        try {
            new ClassPathXmlApplicationContext("spring.xml");
        } catch (BeansException e) {
            Throwable e1 = e.getCause().getCause().getCause();
            throw e1;
        }
    }
}
