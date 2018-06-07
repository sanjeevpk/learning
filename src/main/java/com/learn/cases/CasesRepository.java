/**
 * 
 */
package com.learn.cases;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasesRepository extends CrudRepository<Cases, Long>{

	List<Cases> findByUserId(Long userId);

	List<Cases> findByStatus(String string);

}
