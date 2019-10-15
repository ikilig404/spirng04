package com.dgut.spring.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dgut.spring.jdbc.exception.BalanceNotEnoughException;
import com.dgut.spring.jdbc.exception.QuantityNotEnoughException;

@Repository
public class ShopDaoImpl implements ShopDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getGoodsPriceById(int goodsId) {
		String sql = "select price from tbl_goods where goods_id = ?";
		Integer price = jdbcTemplate.queryForObject(sql, Integer.class, goodsId);
		return price;
	}

	@Override
	public void decreaseGoodsQuantity(int goodsId) {
		String q_sql = "select quantity from tbl_goods where goods_id = ?";
		Integer quantity = jdbcTemplate.queryForObject(q_sql, Integer.class, goodsId);
		if(quantity<=0)
			throw new QuantityNotEnoughException("Quantity of goods is not enough.");
		String sql = "update tbl_goods set quantity = quantity - 1 where goods_id = ?";
		jdbcTemplate.update(sql, goodsId);
	}

	@Override
	public void updateBalance(int memberId, int charge) {
		String b_sql = "select balance from tbl_member where member_id = ?";
		Integer balance = jdbcTemplate.queryForObject(b_sql, Integer.class, memberId);
		if(balance<charge)
			throw new BalanceNotEnoughException("Balance is not enough.");
		String sql = "update tbl_member set balance = balance - ? where member_id = ?";
		jdbcTemplate.update(sql, charge, memberId);
	}

}
