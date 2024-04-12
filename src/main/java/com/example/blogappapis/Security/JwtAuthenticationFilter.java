//package com.example.blogappapis.Security;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter
//{
//    @Autowired
//    @Qualifier
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private  JwtTokenHelper jwtTokenHelper;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException
//    {
//      //1. get Token
//
//        String requestToken = request.getHeader("Authentication");
//        //0      7
//        //Bearer 42972369789762437scggxh -> password go to the String - authentication
//        System.out.println(requestToken);
//
//        String username = null;
//        String token = null;
//
//        if (requestToken!=null && requestToken.startsWith("Bearer"))
//        {
//            token = requestToken.substring(7);
//            try
//            {
//                username = this.jwtTokenHelper.getUsernameFromJwtToken(token);
//            }
//            catch (IllegalArgumentException e)
//            {
//                System.out.println("Unable to get the Jwt token..");
//            }
//            catch(ExpiredJwtException e)
//            {
//                System.out.println("Jwt token has expired");
//            }catch(MalformedJwtException e){
//                System.out.println("Invalid Jwt");
//            }
//            }
//        else {
//            System.out.println("Jwt token does not start with bearer");
//        }
//
//        //once we get the token , now validate
//
//        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
//        {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//            if(this.jwtTokenHelper.validateToken(token, userDetails))
//            {
//                //Then it requires authentication
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//            else {
//                System.out.println("Invalid jwt token ..");
//            }
//        }
//        else {
//            System.out.println("username is null or context is not null..");
//        }
//
//        filterChain.doFilter(request, response);
//     }
//}
