package br.com.pandox.xxx.controller.api;

import br.com.pandox.xxx.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class InvoiceAPI extends BaseController {

//    private static Logger log = Logger.getLogger(InvoiceAPI.class);
//
//    @Autowired
//    private InvoiceService service;
//
//    @RequestMapping(value = "/api/invoice", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public InvoiceDTO create(@Valid @RequestBody InvoiceDTO dto) {
//        return service.persist(dto);
//    }
//
//    @RequestMapping(value = "/invoice", method = RequestMethod.GET, produces = "application/json")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public List<InvoiceDTO> getAll() {
//        return service.getAll();
//    }

}
