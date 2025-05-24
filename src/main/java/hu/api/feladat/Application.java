package hu.api.feladat;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public final class Application {

    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
