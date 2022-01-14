package com.example.league.service;

import com.example.league.domain.Address;
import com.example.league.domain.ROLE;
import com.example.league.domain.User;
import com.example.league.dto.LoginDto;
import com.example.league.dto.SessionDto;
import com.example.league.dto.UserDto;
import com.example.league.exception.UserEmailDuplicateException;
import com.example.league.exception.UserEmailOrPasswordWrongException;
import com.example.league.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public Long signup(UserDto userDto){
        userRepository.findByEmail(userDto.getEmail()).ifPresent(
                a -> {
                    throw new UserEmailDuplicateException("이미 존재하는 EMAIL 입니다.");

                });
        Address address = Address.builder()
                .city(userDto.getCity())
                .street(userDto.getStreet())
                .build();
        ROLE role = makeRole(userDto);
        User member = User.createUser(userDto.getEmail(), userDto.getPassword()
                , userDto.getName(), address,role);
        return userRepository.save(member).getId();
    }

    @Override
    @Transactional
    public SessionDto login(LoginDto loginDto) {
        User member = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(
                () -> {
                    throw new UserEmailOrPasswordWrongException("아이디 혹은 비밀번호가 틀렸습니다.");
                }
        );
        if (!member.getPassword().equals(loginDto.getPassword())){
            throw new UserEmailOrPasswordWrongException("아이디 혹은 비밀번호가 틀렸습니다.");
        }
        return new SessionDto(member.getEmail(),member.getId(),member.getName(),member.getRole());
    }

    private ROLE makeRole(UserDto userDto) {
        if(userDto.getRole().equals("member")){
            return  ROLE.MEMBER;
        }
        else if(userDto.getRole().equals("founder")){
            return  ROLE.TEAM_FOUNDER;
        }
        else{
            return ROLE.LEAGUE_HOST;
        }
    }
}
