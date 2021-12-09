package be.ugent.systemdesign.university.faculty.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.university.faculty.application.event.FacultyEventListener;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyCoursesChangedDomainEvent;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;

@Repository
public class FacultyRepositoryImpl implements FacultyRepository {
	
	@Autowired
	FacultyJPARepository repo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public void save(Faculty _f) {	
		repo.save(_f);
		
		_f.getDegrees().forEach(_d -> {
			_d.getDomainEvents().forEach(domainEvent -> {
				eventPublisher.publishEvent(domainEvent);
			});
			
			_d.clearDomainEvents();
		});
	}
	
	@Override
	public Faculty findByFacultyId(Long _id) {
		return repo.getById(_id);
	}

	@Override
	public Faculty findByFacultyName(String _name) {
		return repo.findByFacultyName(_name);
	}
}
