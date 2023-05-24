package bean.lifecycle.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanLifecycleExample2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BeanLifecycleExample2.class);

        // Retrieve the bean from the context
        MyBean myBean = context.getBean(MyBean.class);

        // Use the bean
        myBean.sayHello();

        // Close the context
        context.close();
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public MyBean myBean() {
        return new MyBean();
    }

    public static class MyBean {

        @PostConstruct
        public void init() {
            System.out.println("@PostConstruct init() method called");
        }

        public void sayHello() {
            System.out.println("Hello, World!");
        }

        @PreDestroy
        public void cleanup() {
            System.out.println("@PreDestroy cleanup() method called");
        }
    }
}
