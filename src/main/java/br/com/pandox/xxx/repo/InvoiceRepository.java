package br.com.pandox.xxx.repo;

import org.springframework.data.repository.CrudRepository;
import br.com.pandox.xxx.model.nf.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
}
