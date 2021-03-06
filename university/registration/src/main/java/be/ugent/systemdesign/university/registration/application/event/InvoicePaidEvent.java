package be.ugent.systemdesign.university.registration.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoicePaidEvent {
	private String invoiceId;
	private String studentNumber;
	private String amount;
};
