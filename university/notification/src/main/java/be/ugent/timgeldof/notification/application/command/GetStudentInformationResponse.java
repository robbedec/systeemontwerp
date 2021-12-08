package be.ugent.timgeldof.notification.application.command;

import be.ugent.timgeldof.notification.application.Response;
import be.ugent.timgeldof.notification.application.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentInformationResponse {
	public String studentId;
	public String studentEmail;
	public String firstName;
	public String lastName;
}
