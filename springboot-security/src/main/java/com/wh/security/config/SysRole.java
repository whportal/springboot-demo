package com.wh.security.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>扩展Spring Security的权限</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@Data
public class SysRole implements GrantedAuthority {

    private Long id;

    private String roleName;

    private String roleDesc;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }
}
