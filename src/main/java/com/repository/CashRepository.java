package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Cash;

@Repository
public interface CashRepository extends JpaRepository<Cash, Integer> {

	void saveAndFlush(Optional<Cash> existingCahModel);

}
