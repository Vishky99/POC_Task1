package com.poc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poc.model.User;
import com.poc.repository.UserRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class Poc1Task1ApplicationTests {

	@Autowired
	private UserRepository uRep;
	
	@Test
	@Order(1)
	public void findOne() {
		User u = new User();
		Date dob = new Date(1997,05,16);
		Date jd = new Date(2021,12,01);
		//u.setId(7L);
		u.setName("Random");
		u.setSurname("Person");
		u.setEmail("Ran@Per.com");
		u.setCity("RandomCity");
		u.setPincode("959875");
		u.setDob(dob);
		u.setJoindate(jd);
		uRep.save(u);
		
		User u1 = new User();
		Date dob1 = new Date(1998,12,05);
		Date jd1 = new Date(2020,07,20);
		//u.setId(7L);
		u1.setName("Second");
		u1.setSurname("Man");
		u1.setEmail("Sec@Man.com");
		u1.setCity("SecondCity");
		u1.setPincode("123456");
		u1.setDob(dob1);
		u1.setJoindate(jd1);
		uRep.save(u1);
		
		User u2 = new User();
		Date dob2 = new Date(1999,11,16);
		Date jd2 = new Date(2021,12,12);
		//u.setId(7L);
		u2.setName("Vishal");
		u2.setSurname("Kumar");
		u2.setEmail("Vishal@Kumar.com");
		u2.setCity("Delhi");
		u2.setPincode("110062");
		u2.setDob(dob2);
		u2.setJoindate(jd2);
		uRep.save(u2);
		
		assertNotNull(uRep.findById(1L).get());
	}
	
	@Test
	@Order(2)
	public void readAll() {
		List<User> list = uRep.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		User u = uRep.findById(1L).get();
		u.setCity("Mumbai");
		uRep.save(u);
		assertNotEquals("Delhi", uRep.findById(1L).get().getCity());
	}
	
	@Test
	@Order(4)
	public void testSearch() {
		assertNotNull(uRep.findUserByName("vishal"));
		assertNotNull(uRep.findUserBySurname("kumar"));
		assertNotNull(uRep.findUserByPincode("110062"));
	}
	
	@Test
	@Order(5)
	public void testSoftDelete() {
		List<User> list = uRep.getSoftDeleted();
		assertNotNull(list);
	}

}
