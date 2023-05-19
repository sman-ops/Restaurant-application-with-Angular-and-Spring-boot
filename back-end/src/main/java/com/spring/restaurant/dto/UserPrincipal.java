package com.spring.restaurant.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.restaurant.model.User;

public class UserPrincipal implements UserDetails {
	
	private User user;
	
	
	@Autowired
	public UserPrincipal(User user) {
	this.user=user;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getAuthorities().forEach(temp -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(temp.getRoleName());
            authorities.add(grantedAuthority);
        });
        return authorities;
    }

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.getActive() == 1;
	}
	
	

}
