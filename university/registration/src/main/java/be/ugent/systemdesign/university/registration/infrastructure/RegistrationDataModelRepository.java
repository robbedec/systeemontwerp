package be.ugent.systemdesign.university.registration.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationDataModelRepository extends JpaRepository<RegistrationDataModel, Integer> {
	
	List<RegistrationDataModel> findByStatus(String status);	
	List<RegistrationDataModel> findByNameAndFirstName(String name, String firstName);
	List<RegistrationDataModel> findByFacultyAndDegree(String faculty, String degree);
	List<RegistrationDataModel> findByEmail(String email);
	List<RegistrationDataModel> findByAccountIdAndIsActive(String accountId, Boolean isActive);
}