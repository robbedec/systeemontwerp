package be.ugent.systemdesign.university.registration.domain;

public interface RegistrationRepository {
	public Registration findOne(Integer id);
	public Registration getActiveRegistration(String studentId);
	public int save(Registration _r);	
	public void removeRegistration(int registrationId);	
}
