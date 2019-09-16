package com.i.designpattern.producer_consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟商店 (进货/销售)
 */
public class ShopQueue {
	private static List<Product> list = new ArrayList<Product>();
	private static int i = 0;

	/**
	 * 生产产品
	 */
	public synchronized void onProduct() {
		if (list.size() >= 5) {
			try {
				System.out.println("------------达到了总数,暂停生产-------");
				this.wait();
			} catch (InterruptedException e) {
			}
			System.out.println("produ wait");
		}

		i++;
		Product product = new Product();
		product.setName("商品" + i);
		list.add(product);
		System.out.println("生产了商品=" + product.getName() + ", 商品总数=" + list.size());
		this.notify();
	}

	/**
	 * 消费产品
	 */
	public synchronized void onCousume() {
		if (list.size() == 0) {
			System.out.println("+++++++++++++++++++++++商品消费完了.等待+++++++++++++++=");
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
			System.out.println("cousu wait");
		}

		Product product = list.get(0);
		list.remove(0);
		System.out.println("消费了获得了商品=" + product.getName() + ", 容器容量" + list.size());
		this.notify();
	}
}
