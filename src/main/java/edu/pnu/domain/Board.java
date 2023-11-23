package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	private String username;
	private String title;
	private String content;
	private String image;
	private Integer count;
	private String tag;
	private String name;
	private String cate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private final Date createDate = new Date();
	
//	@PrePersist
//	public void prePersist() {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String formattedDate = dateFormat.format(createDate);
//		try {
//			this.createDate = dateFormat.parse(formattedDate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
