package fun.huanghai.advisor;

import fun.huanghai.service.NameValidator;
import fun.huanghai.service.impl.NameValidatorImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class AspectJTest2 {

    @Pointcut("execution(* test(..))")
    public void test(){

    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeTest2");
    }

    @After("test()")
    public void afterTest(){
        System.out.println("afterTest2");
    }

    @Around("test()")
    public Object arountTest(ProceedingJoinPoint p){
        System.out.println("before2");
        Object o=null;
        try {
            o = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("after2");
        return o;
    }

    @DeclareParents(value = "fun.huanghai.bean.TestBean+",
    defaultImpl = NameValidatorImpl.class)
    public NameValidator nameValidator;

}
