package app.labs.dept.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dept {
	// @JsonProperty("deptNo") // jackson => 첫번째 문자만 소문자로 변환
	private int deptNo;
	// @JsonProperty("dName") // dName => deptName 권장(DTO)
	private String dName;
	// @JsonProperty("loc")
	private String loc;

}
