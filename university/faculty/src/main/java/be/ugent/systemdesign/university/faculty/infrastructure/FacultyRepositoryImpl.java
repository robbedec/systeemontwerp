package be.ugent.systemdesign.university.faculty.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;

public class FacultyRepositoryImpl implements FacultyRepository {

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public void save(Faculty _f) {
		_f.getDomainEvents().forEach(domainEvent -> {
			eventPublisher.publishEvent(domainEvent);
		});
		
		_f.clearDomainEvents();
	}
}
