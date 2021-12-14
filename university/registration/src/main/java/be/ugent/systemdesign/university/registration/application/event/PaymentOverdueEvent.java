package be.ugent.systemdesign.university.registration.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOverdueEvent {
	private String invoiceId;
	private String studentNumber;
	private String dueDate;
	private String amount;
}
