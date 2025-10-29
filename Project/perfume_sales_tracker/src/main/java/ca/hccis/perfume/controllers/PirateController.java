package ca.hccis.perfume.controllers;

import ca.hccis.perfume.entity.Translate;
import ca.hccis.perfume.util.CisUtilityNetwork;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Controller to administer call to api for pirate translator
 *
 * @author BJM
 * @since 20241107
 */
@Controller
@RequestMapping("/pirate")
public class PirateController {

    private static final Logger logger = LoggerFactory.getLogger(PirateController.class);

    /**
     * Send the user to the pirate view
     *
     * @param model
     * @param session
     * @return To the appropriate view
     * @author BJM
     * @since 20220624
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        //BJM 20241104 Connect to the network to get some json
        //String result = CisUtilityNetwork.connectToApi("https://api.funtranslations.com/translate/pirate.json?text=My%20friend");
        //System.out.println("BJM Network:"+result);

        model.addAttribute("translate", new Translate());

        return "/pirate/translate";
    }

    /**
     * Process the translation
     *
     * @param model
     * @param translate Object containing inputs for the translation
     * @return view to show result
     * @author BJM
     * @since 2024-10-10
     */
    @RequestMapping("/submit")
    public String submitTranslation(Model model, @ModelAttribute("translate") Translate translate) {

        //BJM 20241104 Connect to the network to get some json

        //TODO Assignment 4 - url encode the string (check for non depracted method)
        String textToSend = "";
        try {
            textToSend = URLEncoder.encode(
                    translate.getEnglish(),
                    StandardCharsets.UTF_8.toString()
            );
        } catch (UnsupportedEncodingException e) {
            logger.error("Error encoding"+e.getMessage());
        }
        //Assignment 4 - use JSON object to get the translated (contents / translated)
        String result = CisUtilityNetwork.connectToApi("https://api.funtranslations.com/translate/pirate.json?text="+textToSend);
        System.out.println("BJM Network:"+result);

        JSONObject jsonObject = new JSONObject(result);
        JSONObject innerJsonObject = jsonObject.getJSONObject("contents");
        String translated = innerJsonObject.getString("translated");

        //Put object in model so it can be used on the view (html)
        translate.setPirate(translated);
        model.addAttribute("translate", translate);
        return "pirate/translate";
    }

}
