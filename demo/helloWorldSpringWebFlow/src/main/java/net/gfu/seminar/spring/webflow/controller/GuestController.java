package net.gfu.seminar.spring.webflow.controller;


import net.gfu.seminar.spring.webflow.model.AddGuestForm;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller("guestController")
@Scope("session")
public class GuestController {

    private List<AddGuestForm> guestList = new ArrayList<AddGuestForm>();

    @GetMapping("/addGuest")
    public String setupForm(Model model) {
        AddGuestForm guestForm = new AddGuestForm();
        // you can add defaults here
        model.addAttribute(guestForm);
        return "/addGuest";
    }

    @PostMapping("/addGuest")
    public ModelAndView processForm(@ModelAttribute AddGuestForm addGuestForm,
                                    BindingResult result) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("guest", addGuestForm);
        mav.setViewName("/confirm");

        return mav;
    }

    @GetMapping("/showGuest")
    public String addGuest(@ModelAttribute AddGuestForm addGuestForm) {
        System.out.println("Guest added: "+addGuestForm);
        this.guestList.add(addGuestForm);
        return "true";
    }

    public Collection<AddGuestForm> showGuests() {
        return Collections.unmodifiableCollection(this.guestList);
    }
}
