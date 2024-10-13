package org.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CommonUtils {
    private static JwtTokenProvider jwtTokenProvider;

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        CommonUtils.jwtTokenProvider = jwtTokenProvider;
    }

    public static String getCurrentUsername() {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Token không hợp lệ");
        }

        return jwtTokenProvider.getUsernameFromToken(token.substring(7));
    }
}
