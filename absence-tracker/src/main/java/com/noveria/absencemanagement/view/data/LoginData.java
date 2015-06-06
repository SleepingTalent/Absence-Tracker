package com.noveria.absencemanagement.view.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * This class Manages Login Actions
 *
 * @author lynseymcgregor
 */
@ManagedBean(name = "loginData")
@RequestScoped
public class LoginData {

    private static final Logger logger = LoggerFactory.getLogger(LoginData.class);

    private String userName = null;
    private String password = null;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    /**
     * Login method, authenticates username and password
     *
     * @return String "correct" if successful otherwise "incorrect"
     */
    public String login() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            logger.error(e.getMessage()+":"+this.getUserName()+":"+this.getPassword());

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Unsuccessful", e.getMessage()) );
            return "incorrect";
        }

        return "correct";
    }


    /**
     * Logs the User out by clearing the SecurityContext
     *
     * @return String "loggedout"
     */
    public String logout() {
        SecurityContextHolder.clearContext();
        return "logout";
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
