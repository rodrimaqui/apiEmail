package parcial2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import parcial2.Converter.UserConverter;
import parcial2.Response.UserWrapper;

/**
 * Created by rodri on 12/06/17.
 */
@Configuration
public class Configuracion {

    @Autowired
    AuthFilter authFilter;

    @Bean
    public UserConverter getUserConverter(){
        return new UserConverter();
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}
