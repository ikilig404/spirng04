package com.dgut.spring.jdbc.service;

import java.util.List;

public interface ShopServiceInterface {
	public void sellGoods(int goodsId, int memberId);
	public void checkOut(List<Integer> goods, int memberId);
}
