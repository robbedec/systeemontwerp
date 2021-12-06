package be.ugent.timgeldof.learning_platform.infrastructure.course_access;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course_access")
public class CourseAccessDataModel {
	@Id
	private String studentId;
    @ElementCollection  
	private List<Integer> courseIds;
	private boolean undergoingPlagiarismProcedure;
	private boolean invoiceOpen;
}
