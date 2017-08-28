package intuit.assessment.domain;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author: Jiajie Duan
 * @date: 08/23/17
 */

@Entity
@Table(name = "invoice")
public class Invoice{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "customer_name")
	@NotNull(message = "name cannot be null")
	@Size(min=1, max=50)
	private String name;
	
	@Column(name = "email")
	@NotNull(message = "email cannot be null")
	@Email
	private String email;
	
	@Column(name = "due_date")
	@NotNull(message = "due date cannot be null")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="PST")
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	@Column(name = "amount_total")
	private Double totalAmount;
	
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private Set<InvoiceDetail> invoiceDetails = new HashSet<InvoiceDetail>();
	
	public Invoice(){
		
	}

	public Invoice(String name, String email, Date dueDate, Double totalAmount) {
		super();
		this.name = name;
		this.email = email;
		this.dueDate = dueDate;
		this.totalAmount = totalAmount;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	

    public Set<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
	
}
