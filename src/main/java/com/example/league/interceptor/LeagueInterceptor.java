package com.example.league.interceptor;

import com.example.league.argumentresolver.SessionConst;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.domain.League;
import com.example.league.domain.ROLE;
import com.example.league.domain.request.RequestLeague;
import com.example.league.repository.LeagueRepository;
import com.example.league.repository.RequestLeagueRepository;
import com.example.league.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class LeagueInterceptor implements HandlerInterceptor {

    private final RequestLeagueRepository requestLeagueRepository;
    private final LeagueRepository leagueRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if(session==null){
            response.sendRedirect("/err/league/noLeague");
            return false;
        }

        SessionDto sessionUser = (SessionDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        String requestURI = request.getRequestURI();
        if(requestURI.equals("/league/") && request.getMethod().equals("GET")){
            return true;
        }

        if(!sessionUser.getRole().equals(ROLE.LEAGUE_HOST)){
            response.sendRedirect("/err/league/noAuthority");
            return false;
        }
        if(requestURI.equals("/league/")){
            return true;
        }

        if(requestURI.contains("/league/request") && request.getMethod().equals("GET")){
            Long pathVariable = Long.parseLong(requestURI.substring(16));
            League league = leagueRepository.findById(pathVariable).get();
            if(!league.getEmail().equals(sessionUser.getEmail())){
                response.sendRedirect("/err/league/notAuthority");
                return false;
            }
        }
        else{
            Long pathVariable = Long.parseLong(requestURI.substring(16));
            RequestLeague requestLeague = requestLeagueRepository.findFetchLeagueById(pathVariable).get();
            if(!requestLeague.getLeague().getEmail().equals(sessionUser.getEmail())){
                response.sendRedirect("/err/league/notAuthority");
                return false;
            }
        }
        return true;
    }
}
