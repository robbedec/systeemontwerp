package be.ugent.systemdesign.university.invoice.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import be.ugent.systemdesign.university.invoice.domain.seedwork.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice extends AggregateRoot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String invoiceId;	
	private final String schoolName = "Ugent";
	private final String schoolInvoiceNumber = "BE123456789";	
	
	private String studentFirstName;
	private String studentLastName;
	private String studentNumber;

	private Double amount;
	private LocalDate dueDate;
	private String description;
	private boolean isPaid;
	
	public Invoice(String studentNumber, String studentFirstName, String studentLastName) {
		this.studentNumber = studentNumber;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.dueDate = LocalDate.now().plusDays(14);
		this.isPaid = false;
	}

	public void isPaid() {
		this.isPaid = true;	
		addDomainEvent(new InvoicePaidEvent(studentNumber, amount));
	}
	
	public void calculateAmount(String faculty) {
		if(faculty.equals("Industriële Wetenschappen")) {
			this.amount = 700.00;
		} else {
			this.amount = 400.00;
		}		
	}
	
	
}
