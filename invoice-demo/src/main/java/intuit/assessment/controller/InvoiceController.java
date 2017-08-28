package intuit.assessment.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import intuit.assessment.domain.Invoice;
import intuit.assessment.invoice.InvoiceService;

/**
 * @author: Jiajie Duan
 * @date: 08/23/17
 */

@RestController
public class InvoiceController {


	private final Logger slf4jLogger = LoggerFactory.getLogger(InvoiceService.class);
	
	@Autowired
	private InvoiceService invoiceService;
	
	/**
	 * 
	 * @return List<Invoice>
	 * Return all the invoice
	 */
	@RequestMapping(method=RequestMethod.GET, value="/invoice")
	public List<Invoice> getAllInvoices(){
		return invoiceService.getAllInvoices();
	}
	
	/**
	 * 
	 * @param id
	 * @return ResponseEntity Invoice
	 * Get Invoice by id
	 */
	@RequestMapping(method=RequestMethod.GET, value="/invoice/{id}")
	public ResponseEntity<Invoice> getInvoceById(@PathVariable int id){
		if (!invoiceService.invoiceExists(id)){
			slf4jLogger.info("The invoice ID cannot be found");
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);		
		}
		else
			return new ResponseEntity<> (invoiceService.getInvoiceById(id), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param invoice
	 * @return ResponseEntity invoice
	 * Create Invoice
	 */
	@RequestMapping(method=RequestMethod.POST, value="/invoice")
	public ResponseEntity<Invoice> addInvoice(@Valid @RequestBody Invoice invoice){
		invoiceService.addInvoice(invoice);
		return new ResponseEntity<> (invoice, HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @param id
	 * @return ResponseEntity invoice
	 * Delete invoice by id
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/invoice/{id}")
	public ResponseEntity<Invoice> deleteInvoice(@PathVariable int id){
		if (!invoiceService.invoiceExists(id)){
			slf4jLogger.info("The invoice ID cannot be found");
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);		
		}
		else{
			invoiceService.deleteInvoice(id);
			return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param invoice
	 * @return ResponseEntity invoice
	 * update invoice by id (only name, email, duedate are allowed to be updated)
	 */
	@RequestMapping(method=RequestMethod.PATCH, value="/invoice/{id}")
	public ResponseEntity<Invoice> updateInvoice(@Valid @RequestBody Invoice invoice, @PathVariable int id){
		if (!invoiceService.invoiceExists(id)){
			slf4jLogger.info("The invoice ID cannot be found");
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);		
		}
		else{
			Invoice oldInvoice = invoiceService.updateInvoice(id, invoice);
			return new ResponseEntity<> (oldInvoice, HttpStatus.OK);
		}

	}

}