package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id  = resultSet.getLong("id");
		int orderId  = resultSet.getInt("orderid");
		int customerId = resultSet.getInt("customerid");
		int itemId = resultSet.getInt("itemid");
		return new Order(id,orderId,  customerId, itemId);
	}


	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order order) {

		Random rand = new Random();
		int orderId = rand.nextInt(1000);

		int count=order.getItemIDs().size();
		for(int x=0;x<count;x++) {
			try (Connection connection = DBUtils.getInstance().getConnection();
				 PreparedStatement statement = connection
						 .prepareStatement("INSERT INTO orders(orderid,customerid,itemid) VALUES (?, ?,?)");) {
				statement.setInt(1,orderId); ;
				statement.setInt(2, order.getCustomerId());
				statement.setLong(3,Long.parseLong(order.getItemIDs().get(x)));
				statement.executeUpdate();
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
		}
		return readLatest();
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET customerid = ?, itemid = ? WHERE id = ?");) {
			statement.setInt(1, order.getCustomerId());
			statement.setInt(2, order.getItemId());
			statement.setLong(3, order.getId());
			statement.executeUpdate();
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
