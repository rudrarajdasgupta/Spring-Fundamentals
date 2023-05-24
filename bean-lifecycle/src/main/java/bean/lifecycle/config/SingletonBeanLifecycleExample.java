package bean.lifecycle.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingletonBeanLifecycleExample {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SingletonBeanLifecycleExample.class);

        // Retrieve the bean from the context
        SingletonBean singletonBean = context.getBean(SingletonBean.class);

        // Use the bean
        singletonBean.sayHello();

        // Close the context
        context.close();
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }

    public static class SingletonBean {

        public void init() {
            System.out.println("SingletonBean initialized");
        }

        public void sayHello() {
            System.out.println("Hello, Singleton Bean!");
        }

        public void cleanup() {
            System.out.println("SingletonBean destroyed");
        }
    }
}