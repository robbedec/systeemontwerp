package be.ugent.timgeldof.notification.infrastructure;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.ugent.timgeldof.notification.domain.Student;
import be.ugent.timgeldof.notification.domain.StudentRepository;

@Repository
public class StudentRepositoryImpl implements StudentRepository{
	
	@Autowired
	StudentJPARepository studentJpaRepository;
	
	@Override
	public Student findOne(String id) throws StudentNotFoundException {
		Optional<Student> s = this.studentJpaRepository.findById(id);
		if(s.isEmpty())
			throw new StudentNotFoundException();
		return s.get();
	}
	
	@Override
	public void save(Student s) {
		this.studentJpaRepository.save(s);
	}

	@Override
	public Student findByEmail(String email) {
		Optional<Student> s = this.studentJpaRepository.findByEmailAddress(email);
		if(s.isEmpty())
			throw new StudentNotFoundException();
		return s.get();	
	}
}
