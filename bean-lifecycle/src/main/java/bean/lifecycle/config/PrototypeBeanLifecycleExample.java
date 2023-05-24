package bean.lifecycle.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PrototypeBeanLifecycleExample implements InitializingBean, DisposableBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PrototypeBeanLifecycleExample.class);

        // Retrieve the bean from the context multiple times
        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);

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
    @Scope("prototype")
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    public static class PrototypeBean {
        public PrototypeBean() {
            System.out.println("PrototypeBean initialized");
        }
    }
}
