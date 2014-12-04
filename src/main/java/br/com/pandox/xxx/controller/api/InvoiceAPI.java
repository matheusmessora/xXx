package br.com.pandox.xxx.controller.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.pandox.xxx.controller.BaseController;
import br.com.pandox.xxx.dto.nf.InvoiceDTO;
import br.com.pandox.xxx.service.nf.InvoiceService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class InvoiceAPI extends BaseController {

    private static Logger log = Logger.getLogger(InvoiceAPI.class);

    @Autowired
    private InvoiceService service;

    @RequestMapping(value = "/invoice", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public InvoiceDTO create(@Valid @RequestBody InvoiceDTO dto) {
        return service.persist(dto);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<InvoiceDTO> getAll() {
        return service.getAll();
    }

}
