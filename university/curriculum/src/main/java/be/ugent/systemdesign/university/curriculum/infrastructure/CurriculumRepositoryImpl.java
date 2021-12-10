package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.university.curriculum.domain.Course;
import be.ugent.systemdesign.university.curriculum.domain.Curriculum;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumRepository;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumStatus;

@Repository
public class CurriculumRepositoryImpl implements CurriculumRepository {

	@Autowired
	CurriculumDataModelRepository curriculumDMRepo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public Curriculum findByCurriculumId(String curriculumId) {
		CurriculumDataModel c = curriculumDMRepo.findById(curriculumId).orElseThrow(CurriculumNotFoundException::new);
		return mapToCurriculum(c);
	}
	
	@Override
	public Curriculum findByStudentId(String studentId) {
		CurriculumDataModel c = curriculumDMRepo.findByStudentId(studentId).orElseThrow(CurriculumNotFoundException::new);
		return mapToCurriculum(c);
	}

	@Override
	public void save(Curriculum c) {
		CurriculumDataModel dataModel = mapToCurriculumDataModel(c);
		curriculumDMRepo.save(dataModel).getCurriculumId();
		
		c.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		c.clearDomainEvents();
	}
	
	@Override
	public Curriculum findByStudentIdAndFacultyNameAndDegreeName(String studentId, String facultyName,
			String degreeName) {
		List<CurriculumDataModel> cdms = curriculumDMRepo.findByStudentIdAndFacultyNameAndDegreeName(studentId, facultyName, degreeName);
		
		switch (cdms.size()) {
			case 0:
				throw new CurriculumNotFoundException();
			case 1:
				return mapToCurriculum(cdms.get(0));
			default:
				throw new UnsupportedOperationException();
		}
	}
	
	private CurriculumDataModel mapToCurriculumDataModel(Curriculum _c) {
		return new CurriculumDataModel(
			_c.getCurriculumId(),
			_c.getStudentId(),
			_c.getCurriculumStatus(),
			_c.getDateCreated(),
			_c.getDateLastChanged(),
			_c.getAcademicYear(),
			_c.getCourses(),
			_c.getFacultyName(),
			_c.getDegreeName()
		);
	}
	
	private Curriculum mapToCurriculum(CurriculumDataModel _c) {
		return Curriculum.builder()
			.curriculumId(_c.getCurriculumId())
			.studentId(_c.getStudentId())
			.curriculumStatus(CurriculumStatus.valueOf(_c.getCurriculumStatus()))
			.dateCreated(_c.getDateCreated())
			.dateLastChanged(_c.getDateLastChanged())
			.academicYear(Year.of(_c.getAcademicYear()))
			.courses(_c.getCourses()
					.stream()
					.map(t -> Course.builder()
							.name(t.getName())
							.credits(t.getCredits())
							.build())
					.collect(Collectors.toList()))
			.facultyName(_c.getFacultyName())
			.degreeName(_c.getDegreeName())
			.build();
	}
}
