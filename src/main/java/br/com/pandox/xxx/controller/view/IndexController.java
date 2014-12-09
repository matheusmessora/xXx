package br.com.pandox.xxx.controller.view;

import br.com.pandox.xxx.model.nf.Invoice;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    private static Logger log = Logger.getLogger(IndexController.class);

    @RequestMapping("/")
    public Invoice index() {
        String s = "mama";
        Integer a = 12;
        return new Invoice();
    }

}
