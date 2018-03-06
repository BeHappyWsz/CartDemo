package wsz.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
/**
 * ���ﳵ��
 * @author wsz
 * @date 2018��3��5��
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
	 * �����Ʒ
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
	 * ɾ����Ʒ
	 * @param item
	 * @return
	 */
	public boolean removeGoods(Items item) {
		goods.remove(item);
		return true;
	}
	
	/**
	 * ͳ�ƹ��ﳵ���ܽ��
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
