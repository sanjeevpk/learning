/**
 * 
 */
package com.learn.address;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sanjkul2
 *
 */

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{
	List<Address> findByAdvocateId(Long id);
}
