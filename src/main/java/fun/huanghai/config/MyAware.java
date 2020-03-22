package fun.huanghai.config;

import fun.huanghai.bean.Hello;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class MyAware implements BeanFactoryAware {

    private BeanFactory beanFactory;

    //声明bean的时候Spring会自动注入BeanFactory
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware(){
        //通过hello这个bean id从beanFactory获取实例
        Hello hello = (Hello) beanFactory.getBean("hello");
        hello.say();
    }
}
