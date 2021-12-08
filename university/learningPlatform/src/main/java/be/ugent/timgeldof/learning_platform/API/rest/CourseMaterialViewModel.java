package be.ugent.timgeldof.learning_platform.API.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterialViewModel {
	public String fileName;
	public byte[] file;
}
