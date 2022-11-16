package com.ncs.abc.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ncs.abc.model.AppUser;


@Repository
public interface AppUsersRepository extends JpaRepository<AppUser, Integer> 
{
	/*
	 *  NOTE : where to change
	 *  1. change class name in @Query string 
	 *  2. change bean class name from method return type
	 * */
	
	@Query("from AppUser where username = :username")
	public AppUser getUsersByUsername(String username);
}
