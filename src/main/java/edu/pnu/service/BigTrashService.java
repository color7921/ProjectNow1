package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.BigTrash;
import edu.pnu.persistence.BigTrashRepository;

@Service
public class BigTrashService {

	@Autowired
	private BigTrashRepository bigTrashRepository;
	
	public List<BigTrash> getAllBigTrash(){
		return bigTrashRepository.findAll();
		
	}
	public List<BigTrash> getBigTrash(String sido, String cate, String keyword){
		
		if (cate.equals("전체")) {
			return bigTrashRepository.findBySidoAndNameContaining(sido, keyword);
		}
		return bigTrashRepository.findBySidoAndCateAndNameContaining(sido, cate, keyword);
	}
	//데이터가 객체로 받을때(name
	public List<Object> getCate(String cate, String sido){
	
		return bigTrashRepository.findDistinctByCateContaining(cate, sido);
	}
	public List<String> getName(String cate, String sido, String name) {
	
		return bigTrashRepository.findBySidoAndCateAndName(cate, sido, name);
	}
	
}
