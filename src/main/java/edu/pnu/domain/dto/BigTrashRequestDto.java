package edu.pnu.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BigTrashRequestDto {
	
	
	private String username;
	private String sido;
	private String cate;
	private String size;
	private String name;
	
	private String title;
	private String content;
	private String image;
	private String tag;

	

}
