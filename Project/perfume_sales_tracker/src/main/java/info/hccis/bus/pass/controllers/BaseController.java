package info.hccis.bus.pass.controllers;

import info.hccis.bus.pass.bo.BusPassBO;
import info.hccis.bus.pass.jpa.entity.CodeValue;
import info.hccis.bus.pass.repositories.CodeValueRepository;
import info.hccis.bus.pass.util.CisUtility;

import javax.servlet.http.HttpSession;

import info.hccis.bus.pass.util.CisUtilityNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Base controller which control general functionality in the app.
 *
 * @since 20220624
 * @author BJM
 */
@Controller
public class BaseController {

    private final CodeValueRepository _cvr;

    @Autowired
    public BaseController(CodeValueRepository cvr) {
        _cvr = cvr;
    }

    /**
     * Send the user to the welcome view
     *
     * @since 20220624
     * @author BJM
     */
    @RequestMapping("/")
    public String home(HttpSession session) {


        //BJM 20200602 Issue#1 Set the current date in the session
        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        BusPassBO.setBusPassTypes(_cvr, session);

        return "index";
    }

    /**
     * Send the user to the about view.
     *
     * @since 20220624
     * @author BJM
     */
    @RequestMapping("/about")
    public String about() {
        return "other/about";
    }
}
