package com.example.league.service;

import com.example.league.argumentresolver.SessionDto;
import com.example.league.domain.Address;
import com.example.league.domain.ROLE;
import com.example.league.domain.User;
import com.example.league.exception.UserEmailDuplicateException;
import com.example.league.exception.UserEmailOrPasswordWrongException;
import com.example.league.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long signup(String email, String password, String role,String name,Address address){
        userRepository.findByEmail(email).ifPresent(
                a -> {
                    throw new UserEmailDuplicateException("이미 존재하는 EMAIL 입니다.");

                });
        ROLE userRole = makeRole(role);
        User member = User.createUser(email,password,name,address,userRole);
        return userRepository.save(member).getId();
    }

    @Override
    @Transactional
    public SessionDto login(String email, String password) {
        User member = userRepository.findByEmail(email).orElseThrow(
                () -> {
                    throw new UserEmailOrPasswordWrongException("아이디 혹은 비밀번호가 틀렸습니다.");
                }
        );
        if (!member.getPassword().equals(password)){
            throw new UserEmailOrPasswordWrongException("아이디 혹은 비밀번호가 틀렸습니다.");
        }
        return new SessionDto(member.getEmail(),member.getId(),member.getName(),member.getRole());
    }

    private ROLE makeRole(String role) {
        if(role.equals("member")){
            return  ROLE.MEMBER;
        }
        else if(role.equals("founder")){
            return  ROLE.TEAM_FOUNDER;
        }
        else{
            return ROLE.LEAGUE_HOST;
        }
    }
}
