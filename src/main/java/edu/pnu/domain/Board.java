package edu.pnu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Board {

	@Id
	private Integer post_id;
	private String username;
	private String title;
	private String content;
	private String image;
	private Integer count;
	private String tag;
	private String create_date;
	

	
}
