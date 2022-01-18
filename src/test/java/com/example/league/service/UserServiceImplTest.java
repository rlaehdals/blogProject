package com.example.league.service;

import com.example.league.domain.Address;
import com.example.league.domain.ROLE;
import com.example.league.domain.User;
import com.example.league.dto.UserDto;
import com.example.league.exception.UserEmailDuplicateException;
import com.example.league.exception.UserEmailOrPasswordWrongException;
import com.example.league.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;



    @Test
    void userSignupSuccess() {
        Address address = getAddress();
        User user = getuser(address);
        Long signupuserId = userService.signup(new UserDto(user.getEmail(), user.getPassword(), user.getName()
                ,"ROLE.user",address.getCity(), address.getStreet()));

        User result = userRepository.findById(signupuserId).get();

        assertThat(result.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    void userSignupFailDuplicateEmail(){
        Address address = getAddress();
        User user = getuser(address);
        User duplicateuser = getuser(address);
        Long signupuserId = userService.signup(new UserDto(user.getEmail(), user.getPassword(), user.getName()
                ,"user",address.getCity(), address.getStreet()));

        assertThatThrownBy(() -> userService.signup(new UserDto(duplicateuser.getEmail(),
                duplicateuser.getPassword(), duplicateuser.getName(),"user",
                address.getCity(), address.getStreet()))).isInstanceOf(UserEmailOrPasswordWrongException.class);

    }
    @Test
    void userLoginSuccess(){
        Address address = getAddress();
        User user = getuser(address);
        Long signupuserId = userService.signup(new UserDto(user.getEmail(), user.getPassword(), user.getName()
                ,"user",address.getCity(), address.getStreet()));
        Long signup = userService.signup(new UserDto("rkdlem1", "asdf","kim" ,"user","asdf", "asdf"));
        User result = userRepository.findById(signup).get();

        assertThat(result.getEmail()).isEqualTo("rkdlem1");
    }
    @Test
    void userLoginFailDuplicateEmail(){
        Address address = getAddress();
        User user = getuser(address);
        Long signupUserId = userService.signup(new UserDto(user.getEmail(), user.getPassword(), user.getName()
                ,"user",address.getCity(), address.getStreet()));
        assertThatThrownBy(()->userService.signup(new UserDto("rkdlem48", "asdf", "kim","user","asdf"
                , "asdf"))).isInstanceOf(UserEmailDuplicateException.class);


    }

    private Address getAddress() {
        return Address.builder()
                .street("s")
                .city("a")
                .build();
    }

    private User getuser(Address address) {
        return User.createUser("rkdlem48", "asdf", "kim", address, ROLE.MEMBER);
    }
}