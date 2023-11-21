package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.BigTrash;
import edu.pnu.persistence.BigTrashRepository;

@SpringBootTest
public class Test11 {

	@Autowired
	BigTrashRepository bigTrashRepository;
	
	 @Test
	    public void testGetBigTrash() {
	     
	        List<BigTrash> list = bigTrashRepository.findAll();

	       list.forEach(t -> System.out.println(t));

	          
	    }
}
