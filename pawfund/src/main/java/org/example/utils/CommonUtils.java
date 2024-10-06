package org.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CommonUtils {
    @Autowired
    private static JwtTokenProvider jwtTokenProvider;

    public static String getCurrentUsername() {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Token không hợp lệ");
        }

        return jwtTokenProvider.getUsernameFromToken(token.substring(7));
    }
}
