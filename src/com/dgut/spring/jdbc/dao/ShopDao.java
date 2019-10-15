package com.dgut.spring.jdbc.dao;

public interface ShopDao {
	public int getGoodsPriceById(int goodsId);
	public void decreaseGoodsQuantity(int goodsId);
	public void updateBalance(int memberId, int charge);
}
