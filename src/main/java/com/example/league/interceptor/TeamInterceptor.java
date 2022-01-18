package com.example.league.interceptor;

import com.example.league.argumentresolver.SessionConst;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.domain.ROLE;
import com.example.league.domain.Team;
import com.example.league.domain.User;
import com.example.league.domain.request.RequestTeam;
import com.example.league.repository.RequestTeamRepository;
import com.example.league.repository.TeamRepository;
import com.example.league.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeamInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final RequestTeamRepository requestTeamRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/err/team/noTeam");
            return false;
        }
        SessionDto sessionUser = (SessionDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        String requestURI = request.getRequestURI();

        if (sessionUser.getRole() != ROLE.TEAM_FOUNDER) {
            response.sendRedirect("/err/team/noAuthority");
            return false;
        }

        User user = userRepository.findById(sessionUser.getId()).get();
        if(requestURI.equals("/team/new")){
            return true;
        }
        else if(requestURI.contains("/team/request")){
            long pathVariable = Long.parseLong(requestURI.substring(14));
            if(request.getMethod().equals("GET")){
                Team team = teamRepository.findById(pathVariable).get();
                if(team.getEmail().equals(user.getEmail())){
                    return true;
                }
                else{
                    response.sendRedirect("/err/team/notAuthority");
                    return false;
                }
            }
            else{
                Optional<RequestTeam> requestTeam = requestTeamRepository.findFetchTeamById(pathVariable);
                if(requestTeam.isEmpty()){
                    response.sendRedirect("/err/team/notExistRequest");
                    return false;
                }
                else{
                    String teamFounderEmail = requestTeam.get().getTeam().getEmail();
                    if(teamFounderEmail.equals(sessionUser.getEmail())){
                        return true;
                    }
                    else{
                        response.sendRedirect("/err/team/notAuthority");
                        return false;
                    }
                }
            }
        }
        else if(requestURI.contains("/team/league")){
            long teamId = Long.parseLong(request.getParameter("teamId"));
            Team team = teamRepository.findById(teamId).get();
            if(team.getEmail().equals(sessionUser.getEmail())){
                return true;
            }
            else{
                response.sendRedirect("/err/team/notAuthority");
                return false;
            }
        }
        else{
            long teamId = Long.parseLong(requestURI.substring(6));
            Team team = teamRepository.findById(teamId).get();
            if(team.getEmail().equals(user.getEmail())){
                return true;
            }
            else{
                response.sendRedirect("/err/team/notAuthority");
                return false;
            }
        }
    }
}
