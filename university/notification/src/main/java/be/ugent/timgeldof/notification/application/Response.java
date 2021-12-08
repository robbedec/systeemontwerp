package be.ugent.timgeldof.notification.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {
	final public String message;
	final public ResponseStatus status;
	
	public Response(ResponseStatus status, String message){ 
		this.status = status; 
		this.message = message;
	}
}