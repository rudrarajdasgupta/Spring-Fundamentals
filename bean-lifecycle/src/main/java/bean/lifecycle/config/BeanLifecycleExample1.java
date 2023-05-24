package bean.lifecycle.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanLifecycleExample1 implements InitializingBean, DisposableBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BeanLifecycleExample1.class);

        // Retrieve the bean from the context
        MyBean myBean = context.getBean(MyBean.class);

        // Use the bean
        myBean.sayHello();

        // Close the context
        context.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean's afterPropertiesSet() method called");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean's destroy() method called");
    }

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

    public static class MyBean {

        public void sayHello() {
            System.out.println("Hello, World!");
        }

        // Custom initialization method
        public void init() {
            System.out.println("Custom init() method called");
        }

        // Custom destruction method
        public void cleanup() {
            System.out.println("Custom cleanup() method called");
        }
    }
}
