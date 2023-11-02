package com.niit.KanbanBoardService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ServletOutputStream pw = response.getOutputStream();
        String authHeader= request.getHeader("Authorization");
        System.out.println(authHeader);
        if(request.getMethod().equals("OPTIONS")){
            //if the method is options the request can pass through and no validation of token is required
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request,response);
        }
        else if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("unauthorized");
            pw.println("Missing or Invalid Token");
        }
        else {
            //extract token from the header
            String token = authHeader.substring(7);
            //extract the claims
            Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
            // set the claims in the request attribute and pass it to the next handler
            request.setAttribute("claims", claims);
            //pass the claims in the request, anyone wanting to use it
            filterChain.doFilter(request, response);
        }

    }
}
