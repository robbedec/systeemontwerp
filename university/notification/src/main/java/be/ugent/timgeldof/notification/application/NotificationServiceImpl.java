package be.ugent.timgeldof.notification.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ugent.timgeldof.notification.application.event.EventHandler;
import be.ugent.timgeldof.notification.domain.Student;
import be.ugent.timgeldof.notification.infrastructure.CommunicationProvider;
import be.ugent.timgeldof.notification.infrastructure.StudentNotFoundException;
import be.ugent.timgeldof.notification.infrastructure.StudentRepositoryImpl;

@Component
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	StudentRepositoryImpl repo;
	
	@Autowired
	CommunicationProvider communicationProvider;
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

	@Override
	public Response notifyStudentCurriculumChange(String studentId, String courseName, String changeType) {
		try{
			
			Student s = repo.findOne(studentId);
			StringBuilder messageContent = new StringBuilder();
			messageContent.append("\nCONTENT: Hello " + s.getFullName() + ",\n The course \"" + courseName + "\" has been ");
			if(changeType.equalsIgnoreCase("ADDED"))
				messageContent.append("added to your curriculum");
			else
				messageContent.append("removed from your curriculum");

			communicationProvider.send(s.getEmailAddress(), messageContent.toString());
			return new Response(ResponseStatus.SUCCESS, "student was notified of curriculum change");

		} catch (StudentNotFoundException e) {
			return new Response(ResponseStatus.SUCCESS, "student was not found");
		} catch (RuntimeException e){
			return new Response(ResponseStatus.SUCCESS, "student was not notified of curriculum change");
		}
	}

	@Override
	public Response notifyStudentCourseMaterialVisibility(String studentId, String courseName, String fileName) {
		try{
			Student s = repo.findOne(studentId);
			StringBuilder messageContent = new StringBuilder();
			
			messageContent.append("\nCONTENT: Hello " + s.getFullName() + ",\n The course \"" + courseName + "\" contains new course material:  " + fileName);

			communicationProvider.send(s.getEmailAddress(), messageContent.toString());
			return new Response(ResponseStatus.SUCCESS, "student was notified of new visible course material");
		} catch (StudentNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "student was not found");
		} catch (RuntimeException e){
			return new Response(ResponseStatus.FAIL, "student was not notified of new visible course material");
		}		
	}

	@Override
	public Response registerNewStudentInformation(String studentId, String studentEmail, String firstName, String lastName) {
		try {
			Student s = new Student();
			s.setEmailAddress(studentEmail);
			s.setStudentId(studentId);
			s.setFirstName(firstName);
			s.setLastName(lastName);
			repo.save(s);
			return new Response(ResponseStatus.SUCCESS, "contact info of student with id:" + studentId + " was registered");
		} catch (Exception e) {
			log.error("Registering the new student information failed");
			return new Response(ResponseStatus.FAIL, "Registering the new student information failed");
		}

	}

}
