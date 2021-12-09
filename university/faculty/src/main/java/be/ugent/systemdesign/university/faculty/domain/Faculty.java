package be.ugent.systemdesign.university.faculty.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import be.ugent.systemdesign.university.faculty.domain.seedwork.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "faculties")
public class Faculty extends AggregateRoot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long facultyId;
	private String facultyName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<DegreeProgramme> degrees = new ArrayList<>();
	
	public Faculty(String _name) {
		this.facultyName = _name;
	}
	
	public void addDegree(DegreeProgramme _d) {
		this.degrees.add(_d);
	}
}
