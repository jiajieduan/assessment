package intuit.assessment.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author: Jiajie Duan
 * @date: 08/23/17
 */

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Detail_Id")
	@JsonIgnore
	private Integer id;
	
	@Column(name = "description")
	@NotNull(message = "description cannot be null")
	private String description;
	
	@Column(name = "amount")
	@NotNull(message = "amount cannot be null")
	private double amount;
	
	@JsonIgnore
	@ManyToOne
	private Invoice invoice;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public InvoiceDetail(String description, double amount, Invoice invoice) {
		super();
		this.description = description;
		this.amount = amount;
		this.invoice = invoice;
	}
	
	public InvoiceDetail() {

	}
	
	
}
