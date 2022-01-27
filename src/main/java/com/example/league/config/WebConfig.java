package com.example.league.config;

import com.example.league.argumentresolver.ArgumentResolver;
import com.example.league.interceptor.LeagueInterceptor;
import com.example.league.interceptor.MemberInterceptor;
import com.example.league.interceptor.TeamInterceptor;
import com.example.league.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final RequestTeamRepository requestTeamRepository;
    private final RequestLeagueRepository requestLeagueRepository;
    private final LeagueRepository leagueRepository;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MemberInterceptor(userRepository))
                .addPathPatterns("/member/**")
                .excludePathPatterns("/signup","/login");

        registry.addInterceptor(new TeamInterceptor(userRepository,teamRepository,requestTeamRepository))
                .addPathPatterns("/team/**")
                .excludePathPatterns("/team/","/team/rest");

        registry.addInterceptor(new LeagueInterceptor(requestLeagueRepository,leagueRepository))
                .addPathPatterns("/league/**")
                .excludePathPatterns("/rest");
    }
}