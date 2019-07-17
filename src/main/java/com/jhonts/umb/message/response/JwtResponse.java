package com.jhonts.umb.message.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String name;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String name, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.name = name;
		this.authorities = authorities;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}
	
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}