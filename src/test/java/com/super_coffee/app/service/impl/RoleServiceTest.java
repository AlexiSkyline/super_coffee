package com.super_coffee.app.service.impl;

import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.repository.IRoleRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class RoleServiceTest {
    @Mock
    private IRoleRepository roleRepository;
    @InjectMocks
    private RoleService roleService;
    private Role role;
    List<Role> roleList;

    @BeforeEach
    void setUp() {
        this.role = new Role();
        this.role.set_id( "ADM-1" );
        this.role.setDescription( "ADMIN_ROLE" );
        this.roleList = Collections.singletonList( this.role );
    }

    @Test
    void save()
    {
        when( this.roleRepository.save( any() ) ).thenReturn( this.role );
        Role newRole = this.roleService.save( this.role );

        assertThat( newRole ).isNotNull();
        assertThat( newRole ).isEqualTo( this.role );
        assertThat( newRole.getDescription() ).isEqualTo( this.role.getDescription() );
    }

    @Test
    void findAll()
    {
        when( this.roleRepository.findAllByStatusTrue() ).thenReturn( this.roleList );
        List<Role> allRoles = this.roleService.findAll();

        assertThat( allRoles ).isNotNull();
        assertThat( allRoles.size() ).isEqualTo( 1 );
        assertThat( allRoles.get(0).getDescription() ).isEqualTo( this.role.getDescription() );
    }

    @Test
    void findById()
    {
        when( this.roleRepository.findById( anyString() ) ).thenReturn( Optional.ofNullable( this.role ) );
        Optional<Role> roleFound = this.roleRepository.findById( "ADM-1" );

        assertThat( roleFound ).isNotNull();
        assertThat( roleFound ).isPresent();
        assertThat( roleFound.get().getDescription() ).isEqualTo( this.role.getDescription() );
        assertThat( roleFound.get() ).isEqualTo( this.role );
    }

    @Test
    void findByDescription()
    {
        when( this.roleRepository.findByDescription( anyString() ) ).thenReturn( Optional.ofNullable( this.role ) );
        Optional<Role> roleFound = this.roleRepository.findByDescription( "ADMIN_ROLE" );

        assertThat( roleFound ).isNotNull();
        assertThat( roleFound ).isPresent();
        assertThat( roleFound.get() ).isEqualTo( this.role );
    }

    @Test
    void countAllDocuments()
    {
        when( this.roleRepository.countAllByStatusIsTrue() ).thenReturn( 5 );
        int totalRole = this.roleService.countAllDocuments();

        assertThat( totalRole ).isNotZero();
        assertThat( totalRole == 5 ).isTrue();
        assertThat( totalRole ).isPositive();
    }
}