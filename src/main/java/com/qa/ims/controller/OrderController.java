package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}


	@Override
	public Order create() {
		LOGGER.info("Please Enter the id of Customer");
		String customerId = utils.getString();

		List<String> items = new ArrayList<>();

		boolean nKeyPressed = false;
		while(!nKeyPressed) {

			LOGGER.info("Please Enter the id of Item...  Press n to exit...");

			String itemid = utils.getString();

			if (itemid.equalsIgnoreCase("n")) {
				nKeyPressed = true;
				break;
			}

			items.add(itemid);
		}

		Order order = orderDAO.create(new Order(Integer.parseInt(customerId),items));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter customer id");
		String customerId = utils.getString();
		LOGGER.info("Please enter a item id");
		String itemid = utils.getString();
		Order order = orderDAO.update(new Order(id,0,Integer.parseInt(customerId), Integer.parseInt(itemid)));
		LOGGER.info("Order Updated");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
