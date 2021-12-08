package be.ugent.systemdesign.university.registration.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationDataModelRepository extends JpaRepository<RegistrationDataModel, Integer> {
	
	List<RegistrationDataModel> findByPayementStatus(String payementStatus);
	List<RegistrationDataModel> findByNameAndFirstName(String name, String firstName);
	RegistrationDataModel findByEmail(String email);	
}
