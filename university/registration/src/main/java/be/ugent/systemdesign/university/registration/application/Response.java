package be.ugent.systemdesign.university.registration.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {
	
	private final ResponseStatus status;
	private final String message;	
}
