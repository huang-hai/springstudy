package fun.huanghai;

import fun.huanghai.aware.MyEnvironmentAware;
import fun.huanghai.bean.HelloMessage;
import fun.huanghai.bean.TestBean;
import fun.huanghai.bean.User;
import fun.huanghai.config.MyAware;
import fun.huanghai.converter.String2DateConverter;
import fun.huanghai.event.TestEvent;
import fun.huanghai.lambda.LambdaService;
import fun.huanghai.service.NameValidator;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.util.Date;

public class Application {

    public static void main(String[] args){

//        ConfigurableListableBeanFactory bf=new XmlBeanFactory(new ClassPathResource ("beanfactory.xml"));
//
//
//        BeanFactoryPostProcessor bfpp=(BeanFactoryPostProcessor)bf.getBean("bfpp");
//        bfpp.postProcessBeanFactory(bf);
//        System.out.println(bf.getBean("simpleBean"));

        ApplicationContext application = new ClassPathXmlApplicationContext("property-placeholder-configurer.xml");
        System.out.println(application.getBean(HelloMessage.class));
//        ApplicationContext application = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //BeanFactory application = new XmlBeanFactory(new ClassPathResource("spring.xml"));

//        System.out.println("IOC容器创建完成了");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //User user = (User) application.getBean("user");
//        User huang = (User) application.getBean("huang");
//        User hai = (User) application.getBean("hai");
//        System.out.println(user==hai);
//        User user = (User) application.getBean("testbean");
        //System.out.println(user);
//        System.out.println("kkkkk");

        //Object car = application.getBean("car");
        //获取carFactoryBean本身 加&前缀
//        Object car = application.getBean("&car");
//        System.out.println(car);

//        MyAware myAware = (MyAware) application.getBean("myAware");
//        myAware.testAware();

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

    @Test
    public void test01(){
        ApplicationContext application = new ClassPathXmlApplicationContext("spring02.xml");
        TestEvent event = new TestEvent("hello", "msg");
        application.publishEvent(event);

        MyEnvironmentAware myEnvironmentAware = (MyEnvironmentAware) application.getBean("myEnvironmentAware");
        Environment environment = myEnvironmentAware.getEnvironment();

        System.out.println(environment.getProperty("os.name"));
        System.out.println(environment.getProperty("JAVA_HOME"));

    }

    @Test
    public void test02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("aspect.xml");
        TestBean bean = (TestBean) ac.getBean("test");
        NameValidator nameValidator = (NameValidator) bean;
        String name = "123123";
        System.out.println(nameValidator.validate(name));
        if(!nameValidator.validate(name)){
            bean.test(name);
        }
    }

    //测试lambda表达式
    @Test
    public void testLambda(){
        LambdaService service = (String name) -> {
          return "hello " + name;
        };

        System.out.println(service.sayHello("huangh"));
    }
}
