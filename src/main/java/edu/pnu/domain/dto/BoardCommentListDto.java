package edu.pnu.domain.dto;

import java.util.List;

import edu.pnu.domain.Board;
import edu.pnu.domain.Comment;
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
public class BoardCommentListDto {

	private List<Board> boardList;
	private List<Comment> commentList;
}
