//package ru.kmetha.externalapi.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    public static final String LOGIN_ENDPOINT ="/api/v1/auth/login";
//    public static final String USER_ENDPOINT ="/api/v1/user";
//    public static final String REGISTRATION_ENDPOINT ="/api/v1/auth/register";
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    http
//            .authorizeRequests()
//            .antMatchers("/").permitAll()
//				.anyRequest().authenticated()
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.permitAll()
//				.and()
//			.logout()
//				.permitAll();
//}
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests((requests) -> {
////            requests.antMatchers("/product/all").permitAll();
////            requests.antMatchers("/").permitAll();
////            requests.antMatchers(HttpMethod.GET, "/product").hasAuthority("ADMIN");
////            requests.antMatchers(HttpMethod.POST, "/product").hasAuthority("ADMIN");
////            requests.mvcMatchers(HttpMethod.GET, "/product/{productId}").permitAll();
////            requests.anyRequest().authenticated();
////        });
////        http.httpBasic().disable();
////        http.csrf().disable();
////    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests((requests) -> {
////            requests.antMatchers("/product/all").permitAll();
////            requests.antMatchers("/").permitAll();
////            requests.antMatchers(HttpMethod.GET, "/product").hasAuthority("ADMIN");
////            requests.antMatchers(HttpMethod.POST, "/product").hasAuthority("ADMIN");
////            requests.mvcMatchers(HttpMethod.GET, "/product/{productId}").permitAll();
////        });
////
////        http.authorizeRequests((requests) -> {
////            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) requests.anyRequest()).authenticated();
////        });
////        http.exceptionHandling().accessDeniedPage("/access-denied");
////        http.formLogin();
////        http.httpBasic();
////    }
////
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
