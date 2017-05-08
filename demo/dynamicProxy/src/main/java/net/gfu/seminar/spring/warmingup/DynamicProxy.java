package net.gfu.seminar.spring.warmingup;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class DynamicProxy implements Task, InvocationHandler {

    public static void main(String[] args) {
        DynamicProxy handler = new DynamicProxy();
        Task t = (Task) Proxy.newProxyInstance(Task.class.getClassLoader(), new Class[]{Task.class}, handler);
        t.execute();
    }

    public void execute() {
        System.out.println("Task->execute");
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("InvocationHandler->" + method.getName());
        return null;
    }
}
