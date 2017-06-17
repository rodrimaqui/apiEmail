package parcial2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableScheduling
public class App extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application){
        return application.sources(App.class);
    }
}
