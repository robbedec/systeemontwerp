package be.ugent.timgeldof.notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ugent.timgeldof.notification.infrastructure.StudentNotFoundException;


public interface StudentRepository{
	Student findOne(String id);
	Student findByEmail(String email);
	void save(Student s);
}
