package io.jzheaux.springsecurity.resolutions;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserRepositoryOpaqueTokenIntrospector implements OpaqueTokenIntrospector {
    private final OpaqueTokenIntrospector delegate;
    private final UserRepository users;

    public UserRepositoryOpaqueTokenIntrospector(OpaqueTokenIntrospector delegate, UserRepository users) {
        this.delegate = delegate;
        this.users = users;
    }
    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token){
        OAuth2AuthenticatedPrincipal principal = this.delegate.introspect(token);
        Collection<GrantedAuthority> authorities = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority -> authority.substring(6))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new DefaultOAuth2AuthenticatedPrincipal(principal.getAttributes(),authorities);
    }
}
