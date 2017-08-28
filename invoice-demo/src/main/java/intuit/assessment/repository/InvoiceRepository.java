package intuit.assessment.repository;

import org.springframework.data.repository.CrudRepository;

import intuit.assessment.domain.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

}
