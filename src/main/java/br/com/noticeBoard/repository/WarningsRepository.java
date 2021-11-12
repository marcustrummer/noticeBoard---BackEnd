package br.com.noticeBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.noticeBoard.model.Warning;

@Repository
public interface WarningsRepository extends JpaRepository <Warning, Long> {
	
	public List<Warning> findByTitleContainingIgnoreCase(String title);
	

}
