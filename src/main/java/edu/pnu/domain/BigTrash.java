package edu.pnu.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bigtrash")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bigId")
//@JsonIdentityReference(alwaysAsId = true)
public class BigTrash {
	@Id
	private Integer bigId;
	private String sido;
	private String name;
	private String cate;
	private String size;
	private String pay;
	private Integer price;
	private String manager;
//	@OneToMany(mappedBy="board")
//	private Board bigId;
	

}
