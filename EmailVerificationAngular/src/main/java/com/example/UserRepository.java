package com.example;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
 
	UserEntity findByEmailIdIgnoreCase(String emailId);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="update users set password=:password where user_id=:userId ")
	public void updatePassword(@Param("password") String pass ,   @Param("userId") long id);
	
	//@Query("select users from UserEntity users where users.emailId=?1")
	    public UserEntity findByEmailId(String emailId);
	
	//@Query("select users from UserEntity users where users.password=?1")
	    public UserEntity findByPassword(String password);
	    
	    public UserEntity findByUserId(long userId);
	
}
