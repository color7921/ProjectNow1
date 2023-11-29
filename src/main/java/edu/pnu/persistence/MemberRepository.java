package edu.pnu.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Member;
import java.util.List;


public interface MemberRepository extends JpaRepository<Member, String> {

	List<Member> findByUsername(String username);

}
