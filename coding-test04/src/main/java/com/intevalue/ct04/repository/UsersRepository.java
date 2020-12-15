package com.intevalue.ct04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intevalue.ct04.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>  {
	
}
