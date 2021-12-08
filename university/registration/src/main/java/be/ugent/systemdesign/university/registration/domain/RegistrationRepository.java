package be.ugent.systemdesign.university.registration.domain;

public interface RegistrationRepository {
	public Registration findOne(Integer id);
	public void save(Registration _r);	
}
