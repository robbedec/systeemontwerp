package be.ugent.systemdesign.university.invoice.domain;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int InvoiceId;
	private final String schoolName = "Ugent";
	private final String schoolInvoiceNumber = "BE123456789";	
	
	private String studentFirstName;
	private String studentLastName;
	private String studentNumber;
	
	private Double amount;
	private LocalDate dueDate;
	private String description;
	

}