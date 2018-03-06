package wsz.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
/**
 * 购物车类
 * @author wsz
 * @date 2018年3月5日
 */
public class Cart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private HashMap<Items,Integer> goods;
	
	private Double totalPrice;

	public Cart() {
		this.goods = new HashMap<Items, Integer>();
		this.totalPrice =0.0;
	}

	/**
	 * 添加商品
	 * @param item
	 * @param number
	 * @return
	 */
	public boolean addGoods(Items item,int number) {
		if(goods.containsKey(item)) {
			goods.put(item, goods.get(item)+number);
		}else {
			goods.put(item, number);
		}
		calToPrice();
		return true;
	}
	
	/**
	 * 删除商品
	 * @param item
	 * @return
	 */
	public boolean removeGoods(Items item) {
		goods.remove(item);
		return true;
	}
	
	/**
	 * 统计购物车的总金额
	 * @return
	 */
	public double calToPrice() {
		double sum = 0.0;
		Set<Items> keys = goods.keySet();
		Iterator<Items> it = keys.iterator();
		while(it.hasNext()) {
			Items next = it.next();
			sum += next.getPrice() * goods.get(next);
		}
		this.setTotalPrice(sum);
		
		return this.getTotalPrice();
	}
	
	public HashMap<Items, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<Items, Integer> goods) {
		this.goods = goods;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
