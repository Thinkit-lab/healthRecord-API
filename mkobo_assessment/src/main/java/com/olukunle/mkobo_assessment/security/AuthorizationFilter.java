package com.olukunle.mkobo_assessment.security;
import com.olukunle.mkobo_assessment.constants.StringValues;
import com.olukunle.mkobo_assessment.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter implements Filter {

    private final StaffService staffService;
    private final UUIDEncryptor uuidEncryptor;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            String auth = request.getHeader(StringValues.AUTHORIZATION_KEY);
            if (auth == null) {
                throw new ServletException("Unauthorized access");
            }

            String staffId = uuidEncryptor.encryptUUID(auth);

            if (staffService.validateStaff(staffId)) {
                chain.doFilter(request, response);
            } else {
                throw new ServletException("Unauthorized access");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
        }
    }

}
