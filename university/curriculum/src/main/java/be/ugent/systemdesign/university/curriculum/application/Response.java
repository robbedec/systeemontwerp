package be.ugent.systemdesign.university.curriculum.application;

public class Response {
	
	public final String message;
	public final ResponseStatus status;
	
	public Response(ResponseStatus _status, String _message) {
		this.message = _message;
		this.status = _status;
	}
}
