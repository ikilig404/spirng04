package com.dgut.spring.jdbc.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dgut.spring.jdbc.bean.Member;
import com.dgut.spring.jdbc.dao.ShopDao;
import com.dgut.spring.jdbc.service.ShopServiceInterface;
import com.mchange.v2.c3p0.ComboPooledDataSource;

class TestJdbcTemplate {
	private ConfigurableApplicationContext ctx;
	private ComboPooledDataSource ds;
	private JdbcTemplate template;
	private ShopDao shopDao;
	private ShopServiceInterface shopService;
	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
		ds = ctx.getBean("dataSource", ComboPooledDataSource.class);
		template = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		shopDao = ctx.getBean("shopDaoImpl", ShopDao.class);
		shopService = ctx.getBean("shopService", ShopServiceInterface.class);
	}
	
	@AfterEach
	public void closeContext() {
		ctx.close();
	}

	@Test
	public void testDataSource() throws Exception {
		Connection connection = ds.getConnection();
		System.out.println(connection);
		System.out.println(template);
		
	}
	
	@Test
	public void testCRUD() {
		String sql = "insert into tbl_member (name, balance) values (?,?)";
		template.update(sql, "Bob", 200);
		String sql1 = "select member_id memberId, name, balance from tbl_member where member_id = ?";
		RowMapper<Member> rowMapper = new BeanPropertyRowMapper<>(Member.class);
		Member member = template.queryForObject(sql1, rowMapper, 1);
		System.out.println(member);
	}
	
	@Test
	public void testDao() {
		int price = shopDao.getGoodsPriceById(1);
		System.out.println(price);
		shopDao.decreaseGoodsQuantity(1);
		shopDao.updateBalance(1, price);
	}
	
	@Test
	public void testTx() {
		shopService.sellGoods(1, 1);
	}
	

}
