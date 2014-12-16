package br.com.pandox.xxx.controller.api;

import br.com.pandox.xxx.controller.BaseController;
import br.com.pandox.xxx.model.nf.Invoice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class InvoiceAPI extends BaseController {


    @RequestMapping("/api/")
    public Invoice index() {
        String s = "mama";
        Integer a = 12;
        return new Invoice();
    }


}
