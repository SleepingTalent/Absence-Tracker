package com.noveria.absencemanagement.view.authentication.controller;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.service.annualleave.AnnualLeaveService;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import com.noveria.absencemanagement.view.navigation.NavigationOutcome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class Manages Login Actions
 *
 * @author lynseymcgregor
 */
@ManagedBean(name = "authController")
@RequestScoped
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private String userName = null;
    private String password = null;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    @ManagedProperty(value = "#{messageHelper}")
    private MessageHelper messageHelper;

    @ManagedProperty(value = "#{userModel}")
    private UserModel userModel;

    @ManagedProperty(value = "#{annualLeaveService}")
    AnnualLeaveService annualLeaveService;

    @ManagedProperty(value = "#{userDAO}")
    UserDAO userDAO;

    /**
     * Login method, authenticates username and password
     *
     * @return String "correct" if successful otherwise "incorrect"
     */
    public String login() {

        try {
            Authentication request = new UsernamePasswordAuthenticationToken(getUserName(), getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            logger.error(e.getMessage()+":"+getUserName()+":"+getPassword());

            messageHelper.addErrorMessage("Login Unsuccessful", e.getMessage());
            return NavigationOutcome.FAIL.getOutcomeName();
        }

        try {
            User user = userDAO.findUserByUsername(getUserName());
            Employee employee = annualLeaveService.findEmployeeByUser(user);

            userModel.setEmployee(employee);
        } catch (NoResultException nre) {
            logger.warn("No Employee found for : " + getUserName() + ":" + getPassword());
        }

        return NavigationOutcome.SUCCESS.getOutcomeName();
    }


    /**
     * Logs the User out by clearing the SecurityContext
     *
     * @return String "loggedout"
     */
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null){
            new SecurityContextLogoutHandler().logout(
                    (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(),
                    (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),
                    authentication);
        }

        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        setUserName(null);
        setPassword(null);
        userModel.setEmployee(null);

        return NavigationOutcome.SUCCESS.getOutcomeName();
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
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

    public AnnualLeaveService getAnnualLeaveService() {
        return annualLeaveService;
    }

    public void setAnnualLeaveService(AnnualLeaveService annualLeaveService) {
        this.annualLeaveService = annualLeaveService;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public EmployeeViewBean getEmployeeDetails() {
        EmployeeViewBean employeeViewBean = new EmployeeViewBean();
        employeeViewBean.setFirstname(userModel.getEmployee().getFirstName());
        employeeViewBean.setLastname(userModel.getEmployee().getLastName());
        employeeViewBean.setId(userModel.getEmployee().getId());
        return employeeViewBean;
    }
}
