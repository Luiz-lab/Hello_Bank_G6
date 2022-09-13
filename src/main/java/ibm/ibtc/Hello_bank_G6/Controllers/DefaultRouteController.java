package ibm.ibtc.Hello_bank_G6.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")

public class DefaultRouteController {


//    @GetMapping("/")
//    public ModelAndView getAll() {
//        return new ModelAndView("redirect:" + "https://google.com");
//    }

//    @GetMapping("/")
//    public ResponseEntity<Object> getAll() {
//        return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
//            public final Object message = "message";
//        });
//    }

    @GetMapping("/")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

}
