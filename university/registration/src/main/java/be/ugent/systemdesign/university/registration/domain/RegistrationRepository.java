package be.ugent.systemdesign.university.registration.domain;

public interface RegistrationRepository {
	public Registration findOne(Integer id);
	public Registration getActiveRegistration(String studentId);
	public void save(Registration _r);	
	public void removeRegistration(int registrationId);	
}
