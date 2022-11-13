package com.spacecodee.library_book_backend.model.dto.user.client;

import com.spacecodee.library_book_backend.entity.UserClientEntity;
import com.spacecodee.library_book_backend.mappers.user.client.IUserClientReadMapper;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PUserClientDto implements Serializable, UserDetails {

    private String fullName;
    private String email;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static PUserClientDto build(UserClientEntity entity) {
        return IUserClientReadMapper.INSTANCE.entityToPDto(entity);
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
        return true;
    }
}
