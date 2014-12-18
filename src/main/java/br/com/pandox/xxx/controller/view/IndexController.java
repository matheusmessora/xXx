package br.com.pandox.xxx.controller.view;

import br.com.pandox.xxx.model.nf.Invoice;
import br.com.pandox.xxx.repo.InvoiceRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class IndexController {

    private static Logger log = Logger.getLogger(IndexController.class);

    @Autowired
    private InvoiceRepository repository;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");

        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setCoo("AAA");
        repository.save(invoice);


        mav.addObject("invoices", repository.findAll());
        return mav;
    }

}
