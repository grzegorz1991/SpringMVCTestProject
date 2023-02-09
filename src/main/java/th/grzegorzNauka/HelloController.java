package th.grzegorzNauka;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello_world", method = RequestMethod.GET)
    public String printHelloWorld(ModelMap modelMap){

        // add attribute to load modelMap
        modelMap.addAttribute("message",
                "Hello World and Welcome to Spring MVC!");

        // return the name of the file to be loaded "hello_world.jsp"
        return "hello_world";
    }

    @RequestMapping(value = "/hello_world2", method = RequestMethod.GET)
    public String printHelloWorld2(ModelMap modelMap){

        // add attribute to load modelMap

        modelMap.addAttribute("message",
                "Hello World 2 and Welcome to Spring MVC!");
        System.out.println("Hello1");
        // return the name of the file to be loaded "hello_world.jsp"
        return "hello_world2";
    }

}