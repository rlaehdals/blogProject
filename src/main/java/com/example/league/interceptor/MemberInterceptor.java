package com.example.league.interceptor;

import com.example.league.argumentresolver.SessionConst;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.domain.ROLE;
import com.example.league.domain.User;
import com.example.league.exception.CanNotFindUser;
import com.example.league.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MemberInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if(session==null){
            response.sendRedirect("/err/member/noUser");
            return false;
        }
        SessionDto sessionUser = (SessionDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(
                () -> {
                    throw new CanNotFindUser("해당 유저는 없습니다.");
                }
        );

        if(sessionUser.getRole()!=ROLE.MEMBER){
            response.sendRedirect("/err/member/notAuthority");
            return false;
        }
        return true;
    }
}
