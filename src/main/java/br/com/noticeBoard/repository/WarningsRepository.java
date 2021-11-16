package br.com.noticeBoard.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import br.com.noticeBoard.model.Warning;

@Repository
public interface WarningsRepository extends JpaRepository <Warning, Long> {
	
	Page<Warning> findByPublished(boolean published, Pageable pageable);
	Page<Warning> findByTitleContaining(String title, Pageable pageable);
	public List<Warning> findByTitleContainingIgnoreCase(String title);
	
	

}
