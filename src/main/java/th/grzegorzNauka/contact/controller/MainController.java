package th.grzegorzNauka.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import th.grzegorzNauka.contact.dao.ContactDAO;
import th.grzegorzNauka.contact.model.Contact;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    public ContactDAO contactDAO;

    @RequestMapping(value = "/")
    public ModelAndView listContact(ModelAndView modelAndView){
    List<Contact> contactList = contactDAO.list();
    modelAndView.addObject("listContact", contactList);
    modelAndView.setViewName("index");

        return modelAndView;
    }
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView modelAndView){

        Contact newContact = new Contact();

        modelAndView.addObject("contact", newContact);
        modelAndView.setViewName("contact_form");

        return modelAndView;
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute Contact contact){
        contactDAO.save(contact);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteContact(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        contactDAO.delete(contactId);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editContact(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Contact contact = contactDAO.get(id);
        ModelAndView model = new ModelAndView("contact_form");
        model.addObject("contact", contact);

        return model;
    }
}
