package com.kimbob.pAIk.domain.user.entity;

import com.kimbob.pAIk.domain.common.entity.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Getter @Setter @ToString @NoArgsConstructor
@Entity
public class Role extends AbstractEntity implements GrantedAuthority {

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }
}
