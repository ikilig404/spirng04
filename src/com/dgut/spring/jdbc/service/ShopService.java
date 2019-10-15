package com.dgut.spring.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.spring.jdbc.dao.ShopDao;

@Service
public class ShopService implements ShopServiceInterface {
	@Autowired
	private ShopDao shopDao;
//	@Autowired
//	private ShopServiceInterface shopService;
	
	@Transactional()
	public void sellGoods(int goodsId, int memberId) {
		
		int price = shopDao.getGoodsPriceById(goodsId);
		shopDao.decreaseGoodsQuantity(goodsId);
		shopDao.updateBalance(memberId, price);
	}
	
	@Transactional
	public void checkOut(List<Integer> goods, int memberId) {
		for(Integer goodsId : goods) {
			//shopService.sellGoods(goodsId, memberId);
		}
	}
}
