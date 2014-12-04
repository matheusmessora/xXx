package br.com.pandox.xxx.service.nf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.pandox.xxx.dto.nf.InvoiceDTO;
import br.com.pandox.xxx.model.nf.Invoice;
import br.com.pandox.xxx.repo.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public InvoiceDTO persist(InvoiceDTO dto) {
        Invoice entity = new Invoice(dto);
        entity = repository.save(entity);
        return new InvoiceDTO(entity);
    }

    public List<InvoiceDTO> getAll() {
        List<InvoiceDTO> list = new ArrayList<>();

        Iterable<Invoice> invoices = repository.findAll();

        for (Invoice invoice : invoices) {
            list.add(new InvoiceDTO(invoice));
        }

        return list;
    }
}
