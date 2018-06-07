/**
 * 
 */
package com.learn.cases;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasesRepository extends CrudRepository<Cases, Long>{

}
