package ibm.ibtc.Hello_bank_G6.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class DefaultRouteController {

    @GetMapping("/")
    public ModelAndView welcome() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

}
