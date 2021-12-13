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
	public Response notifyStudentCurriculumChange(String courseName, String changeType) {
		try{
			// this could be used to register which courses the student should receive notifications from, but considering this is 
			// a stub implementation, it won't be implemented
			StringBuilder messageContent = new StringBuilder();
			messageContent.append("\nCONTENT: Hello,\n The course \"" + courseName + "\" has been ");
			if(changeType.equalsIgnoreCase("ADDED"))
				messageContent.append("added to your curriculum");
			else
				messageContent.append("removed from your curriculum");

			communicationProvider.send("subscribers", messageContent.toString());
			return new Response(ResponseStatus.SUCCESS, "student was notified of curriculum change");

		} catch (StudentNotFoundException e) {
			return new Response(ResponseStatus.SUCCESS, "student was not found");
		} catch (RuntimeException e){
			return new Response(ResponseStatus.SUCCESS, "student was not notified of curriculum change");
		}
	}

	@Override
	public Response notifyStudentCourseMaterialVisibility(String courseName, String fileName) {
		try{
			StringBuilder messageContent = new StringBuilder();
			
			messageContent.append("\n The course \"" + courseName + "\" contains new course material:  " + fileName);

			communicationProvider.send("subscribers", messageContent.toString());
			return new Response(ResponseStatus.SUCCESS, "student was notified of new visible course material");
		} catch (StudentNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "student was not found");
		} catch (RuntimeException e){
			return new Response(ResponseStatus.FAIL, "student was not notified of new visible course material");
		}		
	}

	@Override
	public Response registerNewRegistration(String accountId, String email, String degree) {
		try {
			Student s = new Student();
			s.setEmail(email);
			s.setStudentId(accountId);
			s.setDegree(degree);
			repo.save(s);
			return new Response(ResponseStatus.SUCCESS, "contact info of student with email:" + email + " was registered");
		} catch (Exception e) {
			log.error("Registering the new student information failed");
			return new Response(ResponseStatus.FAIL, "Registering the new student information failed");
		}
	}
	
}
