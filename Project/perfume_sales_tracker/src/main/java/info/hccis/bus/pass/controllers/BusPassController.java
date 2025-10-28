package info.hccis.bus.pass.controllers;

import info.hccis.bus.pass.bo.BusPassBO;
import info.hccis.bus.pass.bo.BusPassValidationBO;
import info.hccis.bus.pass.jpa.entity.BusPass;
import info.hccis.bus.pass.repositories.BusPassRepository;
import info.hccis.bus.pass.repositories.CodeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller to administer bus passes. Note that the code was taken from
 * Fred Campos' project from 2021 which also had modifications from Ferhad in
 * 2022.
 *
 * @author BJM
 * @since 20241021
 */
@Controller
@RequestMapping("/buspass")
public class BusPassController {

    private final BusPassRepository _bpr;
    private final CodeValueRepository _cvr;

    @Autowired
    public BusPassController(BusPassRepository bpr, CodeValueRepository cvr) {
        _bpr = bpr;
        _cvr = cvr;
    }

    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(BusPassController.class);

    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        Iterable<BusPass> busPasses = _bpr.findAll();
        model.addAttribute("busPasses", busPasses);
        model.addAttribute("busPass", new BusPass());
        return "buspass/list";
    }

    /**
     * Page to delete a bus pass
     *
     * @param id ID
     * @return redirect to the list page
     * @author BJM
     * @since 20241021
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        _bpr.deleteById(id);
        return "redirect:/buspass";
    }

    /**
     * Page to add new entity. Taken from tutor app from 2022 (which was
     * also derived from class samples)
     *
     * @param model
     * @return add
     * @author BJM
     * @since 2024-10-24
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {

        BusPassBO.setBusPassTypes(_cvr, session);
        BusPassBO.setBusPassTypes(_cvr, session);
        BusPass busPass = new BusPass();
        BusPassBO.setBusPassDefaults(busPass);
        model.addAttribute("busPass", busPass);
        return "buspass/add";
    }

    /**
     * Page to edit
     *
     * @param id    ID
     * @param model
     * @author BJM
     * @since 20241025
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

        BusPassBO.setBusPassTypes(_cvr, session);

        Optional busPass = _bpr.findById(id);
        if (busPass.isPresent()) {
            model.addAttribute("busPass", busPass.get());
            return "buspass/add";
        }

        //todo How can we communcicate this to the view.
        model.addAttribute("message", "Could not load the bus pass");
        return "redirect:/buspass";
    }


    /**
     * Submit method that processes add and edit and any form submission
     *
     * @param model
     * @param request
     * @param busPass       what is being added or modified
     * @param bindingResult Result of SQL
     * @return add with errors or busPass
     * @author CIS2232
     * @since 20241024
     */
    @RequestMapping("/submit")
    public String submit(Model model, HttpServletRequest request, @Valid @ModelAttribute("busPass") BusPass busPass, BindingResult bindingResult) {
        boolean valid = true;

        BusPassValidationBO bpvbo = new BusPassValidationBO();
        ArrayList<String> validationErrorsStartDate = bpvbo.validateStartDate(busPass);
        if(validationErrorsStartDate.size() > 0) {
            valid = false;
        }

        if (!valid || bindingResult.hasErrors()) {
            System.out.println("--------------------------------------------");
            System.out.println("Validation error - BJM");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
            }
            System.out.println("--------------------------------------------");
            busPass.setCost(new BigDecimal(0));
            model.addAttribute("busPass", busPass);
            model.addAttribute("businessValidationErrorsStartDate", validationErrorsStartDate);
            return "buspass/add";
        }

        BusPassBO.calculateBusPassCost(busPass);
        _bpr.save(busPass);
        return "redirect:/buspass";
    }

    /**
     * Search for a customer name
     *
     * @param model
     * @param busPass
     * @return view for list
     * @author BJM
     * @since 2024-10-31
     */
    @RequestMapping("/search")
    public String search(Model model, @ModelAttribute("busPass") BusPass busPass) {

        //**********************************************************************
        //Use repository method created to find any entities which contain
        //the name entered on the list page.
        //**********************************************************************
        model.addAttribute("busPasses", _bpr.findByNameContaining(busPass.getName()));
        logger.debug("searched for busPass name:" + busPass.getName());
        return "buspass/list";
    }
}
