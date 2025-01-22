package AutoBase.utils;

import AutoBase.dao.DriverRepository;
import AutoBase.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DRIVER"))) {

            Driver driver = driverRepository.findByUserEmail(userDetails.getUsername());

            if (driver != null) {
                HttpSession session = request.getSession();
                session.setAttribute("driverId", driver.getId());

            }
        }
        response.sendRedirect("/home");
    }
}
