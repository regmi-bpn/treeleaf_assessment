package com.example.treeleaf_assessment.commons;

import com.example.treeleaf_assessment.commons.config.CustomAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    private AuthUtil() {
    }

    public static String getLoggedInUserId() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            if (SecurityContextHolder.getContext().getAuthentication() instanceof CustomAuthentication authentication) {
                return authentication.getUserId();
            }
        }
        return null;
    }

    public static String getLoggedInUserName() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            if (SecurityContextHolder.getContext().getAuthentication() instanceof CustomAuthentication authentication) {
                return authentication.getName();
            }
        }
        return null;
    }
}
