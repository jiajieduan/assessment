package intuit.assessment.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import intuit.assessment.controller.InvoiceController;
import intuit.assessment.domain.Invoice;
import intuit.assessment.domain.InvoiceDetail;
import intuit.assessment.invoice.InvoiceService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InvoiceController.class, secure = false)
public class InvoiceControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InvoiceService invoiceService;

	String exampleInvoiceJson = "{\"name\":\"tet556767\", \"email\":\"11000000@123.com\", \"dueDate\":\"2012-12-09\", \"invoiceDetails\":[  {  \"description\":\"dog\",\"amount\":3.89},{  \"description\":\"cat\",\"amount\":12.01}]}";
	
	@Test
	public void createInvoice() throws Exception {

		// invoiceService.addInvoice to respond back with mockInvoice
		Mockito.doNothing()
	       	   .when(invoiceService)
	       	   .addInvoice(Mockito.any(Invoice.class));
       // send invoice as body
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/invoice")
				.accept(MediaType.APPLICATION_JSON).content(exampleInvoiceJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void getInvoice() throws Exception {
		Invoice mockInvoice = createSampleInvoice();
		
		Mockito.when(
				invoiceService.invoiceExists(Mockito.anyInt())).thenReturn(true);
		Mockito.when(
				invoiceService.getInvoiceById(Mockito.anyInt())).thenReturn(mockInvoice);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/invoice/9").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse());
		String expected = "{}";
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void deleteInvoice() throws Exception {		
		Mockito.when(
				invoiceService.invoiceExists(Mockito.anyInt())).thenReturn(true);
		Mockito.doNothing()
    	   	   .when(invoiceService)
    	       .deleteInvoice(Mockito.anyInt());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				"/invoice/10000").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse());

		assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

	}
	
	@Test
	public void updateInvoice() throws Exception {
		Invoice mockInvoice = createSampleInvoice();

		// invoiceService.update to respond back with mockInvoice
		Mockito.when(
				invoiceService.invoiceExists(Mockito.anyInt())).thenReturn(true);
		Mockito.when(
				invoiceService.getInvoiceById(Mockito.anyInt())).thenReturn(mockInvoice);

       // send invoice as body
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.patch("/invoice/10000")
				.accept(MediaType.APPLICATION_JSON).content(exampleInvoiceJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	private Invoice createSampleInvoice(){
		Invoice invoice =new Invoice();
		InvoiceDetail invoiced = new InvoiceDetail();
		invoiced.setDescription("Junit_test_1");
		invoiced.setAmount(12.21);
		invoiced.setInvoice(invoice);
		InvoiceDetail invoiced2 = new InvoiceDetail();
		invoiced2.setDescription("Junit_test_2");
		invoiced2.setAmount(1.22);
		invoiced2.setInvoice(invoice);
		Date duedate = parseDate("2018-02-14");
		Set<InvoiceDetail> listInvoice = new HashSet<>();
		listInvoice.add(invoiced);
		listInvoice.add(invoiced2);
		invoice.setEmail("junit@test.com");
		invoice.setDueDate(duedate);
		invoice.setName("Junit");
		invoice.setTotalAmount(13.43);
		invoice.setInvoiceDetails(listInvoice);
		
		return invoice;
	}
	
	 private Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
}
	
	
