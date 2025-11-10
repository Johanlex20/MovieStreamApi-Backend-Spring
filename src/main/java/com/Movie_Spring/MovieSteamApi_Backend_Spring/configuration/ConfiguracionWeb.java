package com.Movie_Spring.MovieSteamApi_Backend_Spring.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionWeb {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("https://streamgood.modoblu.com", "http://localhost:4200")
                        .allowedMethods("*")
                        .exposedHeaders("*")
                        .allowCredentials(true);  // <--- importante si envías cookies o cabeceras de autorización
            }
        };
    }
}
