package com.super_coffee.app.service.impl;

import com.super_coffee.app.models.domain.User;
import com.super_coffee.app.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class UserServiceTest
{
    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private UserService userService;
    private User user;
    private List<User> userList;

    @BeforeEach
    void setUp()
    {
        this.user = new User();
        this.user.set_id( "USER_TEST1" );
        this.user.setName( "AlexiSkyline_TEST" );
        this.user.setEmail( "ilegal_sprite@outlook.com" );
        this.user.setPassword( "123456" );
        this.userList = Collections.singletonList( this.user );
    }

    @Test
    void findAll()
    {
        when( this.userRepository.findAllByStatusTrue() ).thenReturn( this.userList );
        List<User> allUsers = this.userService.findAll();

        assertThat( allUsers ).isNotNull();
        assertThat( allUsers.size() ).isEqualTo( 1 );
        assertThat( allUsers.get(0).getName() ).isEqualTo( this.user.getName() );
    }

    @Test
    void findById()
    {
        when( this.userRepository.findById( anyString()) ).thenReturn( Optional.ofNullable( this.user ) );
        User userFound = this.userService.findById( "USER_TEST1" );

        assertThat( userFound ).isNotNull();
        assertThat( userFound.getName() ).isEqualTo( this.user.getName() );
        assertThat( userFound ).isEqualTo( this.user );
    }

    @Test
    void countAllDocuments()
    {
        when( this.userRepository.countAllByStatusIsTrue() ).thenReturn( 5 );
        int totalUsers = this.userService.countAllDocuments();

        assertThat( totalUsers ).isNotZero();
        assertThat( totalUsers == 5 ).isTrue();
        assertThat( totalUsers ).isPositive();
    }
}