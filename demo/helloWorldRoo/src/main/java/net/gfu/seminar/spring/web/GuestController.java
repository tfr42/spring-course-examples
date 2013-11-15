package net.gfu.seminar.spring.web;

import net.gfu.seminar.spring.domain.Guest;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/guests")
@Controller
@RooWebScaffold(path = "guests", formBackingObject = Guest.class)
public class GuestController {
}
