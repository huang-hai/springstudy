package fun.huanghai.Invocation;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    //目标对象
    private Object target;

    /**
     * 构造方法传入目标对象
     * @param target
     */
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        //在目标对象的方法执行之前简单打印一下
        System.out.println("----------------before---------------");

        //执行目标对象的方法
        Object result = method.invoke(target,objects);

        //在目标对象的方法执行之后简单打印一下
        System.out.println("----------------after---------------");

        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(),this);
    }
}
