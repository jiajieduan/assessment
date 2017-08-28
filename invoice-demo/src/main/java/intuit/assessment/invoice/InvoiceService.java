package intuit.assessment.invoice;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import intuit.assessment.domain.Invoice;
import intuit.assessment.domain.InvoiceDetail;
import intuit.assessment.repository.InvoiceRepository;


/**
 * @author: Jiajie Duan
 * @date: 08/23/17
 */

@ControllerAdvice
public class InvoiceService {
	
	@Autowired
    private InvoiceRepository invoiceRepository;
	
	public List<Invoice> getAllInvoices(){
		return (List<Invoice>) invoiceRepository.findAll();
		
	}
	
	public Invoice getInvoiceById(int id){
		return  invoiceRepository.findOne(id);
	}
	
	public void addInvoice(Invoice invoice) {
		double totalAmount = 0;
		for(InvoiceDetail detail : invoice.getInvoiceDetails()) {
			detail.setInvoice(invoice);
			totalAmount=totalAmount + detail.getAmount();
		}
		//format double total amount
		totalAmount = Math.floor(totalAmount * 100) / 100;
		invoice.setTotalAmount(totalAmount);
		invoiceRepository.save(invoice);		
	}
	
	public void deleteInvoice (int id){
		invoiceRepository.delete(id);
	}
	
	public Invoice updateInvoice(int id, Invoice invoice) {
		Invoice oldInvoice = invoiceRepository.findOne(id);
		oldInvoice.setDueDate(invoice.getDueDate());
		oldInvoice.setEmail(invoice.getEmail());
		oldInvoice.setName(invoice.getName());
		invoiceRepository.save(oldInvoice);	
		return oldInvoice;
	}
	
	public boolean invoiceExists(int id){
		return  invoiceRepository.exists(id);
	}

}
