package med.voll.api.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedOrigins("http://locahost:3000").allowedMethods("GET", "POST", "DELETE",
                "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

}
