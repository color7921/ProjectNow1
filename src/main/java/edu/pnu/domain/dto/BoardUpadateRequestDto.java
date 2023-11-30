package edu.pnu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BoardUpadateRequestDto {

		private Integer bigId;
		private Integer postId;
		private String title;
		private String content;
		private String tag;
		
}
