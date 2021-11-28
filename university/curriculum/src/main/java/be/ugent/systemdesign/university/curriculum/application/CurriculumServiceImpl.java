package be.ugent.systemdesign.university.curriculum.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.curriculum.domain.Curriculum;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumRepository;

@Service
@Transactional
public class CurriculumServiceImpl implements CurriculumService {
	
	@Autowired
	CurriculumRepository curriculumRepo;

	@Override
	public Response changeCurriculum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response acceptCurriculum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response rejectCurriculum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response markCurriculumAsProposed(String curriculumId) {

		try {
			Curriculum c = curriculumRepo.findByCurriculumId(curriculumId);
		} catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "Could not find curriculum with id " + curriculumId);
		}
		
		return new Response(ResponseStatus.SUCCESS, "");
	}

	@Override
	public Response noteNewRegistration(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response noteDisenrollment(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
