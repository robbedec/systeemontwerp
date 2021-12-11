package be.ugent.timgeldof.notification.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ugent.timgeldof.notification.domain.Student;

public interface StudentJPARepository extends JpaRepository<Student, Integer>{
	Optional<Student> findByEmailAddress(String emailAddress);
}
