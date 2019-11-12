package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.IAuthenticationFacade;
import pl.coderslab.charity.repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private IAuthenticationFacade authenticationFacade;
    private UserRepository userRepository;

    public SecurityConfig(DataSource dataSource, IAuthenticationFacade authenticationFacade, UserRepository userRepository) {
        this.dataSource = dataSource;
        this.authenticationFacade = authenticationFacade;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, true FROM users WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, role FROM users WHERE email = ?"); //do poprawy
    }

    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/datek").permitAll()
                .antMatchers("/potwierdzenie").permitAll()
                .antMatchers("/rejestracja").permitAll()
                .antMatchers("/instytucje/**").hasRole("admin")
                .antMatchers("/administratorzy/**").hasRole("admin")
                .antMatchers("/uzytkownicy/**").hasRole("admin")
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        if(!(authentication instanceof AnonymousAuthenticationToken)){
                            Long userId = userRepository.getUserByEmail(authentication.getName()).getId();
                            String userRole = userRepository.getUserByEmail(authentication.getName()).getRole();
                            if (userRole.equals("admin")){
                                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin/" + userId);
                            } else {
                                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/moje-konto/" + userId);
                            }
                             //dostęp do użytkownika lub admina
                        } else {
                            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/login");
                        }
                    }
                })
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .and().csrf().disable();

    }

}
