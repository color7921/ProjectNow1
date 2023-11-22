package edu.pnu.service;

import edu.pnu.domain.Board;

public interface BoardService {

	void insertBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(Board board);
	void getBoard(Board board);
}
