package com.jawda.service;

import com.jawda.dao.implementation.OrderDAOImpl;
import com.jawda.model.Order;
import com.jawda.model.OrderStatus;
  // Change CommandService to OrderService
import com.jawda.util.PersistenceUtil;

import java.util.List;

import javax.persistence.EntityManager;


public class OrderService  {
    private OrderDAOImpl orderDAO; // Renamed the DAO reference
    EntityManager em = PersistenceUtil.getEntityManager();
    public OrderService() {
        this.orderDAO = new OrderDAOImpl(em); // Updated to use OrderDao
    }

    
    public List<Order> getOrdersForClient(int clientId) { // Updated method name
        return orderDAO.getOrdersByClientId(clientId); // Updated method name
    }

    
    public Order findOrderById(int id) { // Updated method name
        return orderDAO.findById(id); // Updated method name
    }

    public boolean cancelOrder(int orderId) { // Updated method name
        Order order = orderDAO.findById(orderId);
        if (order != null && (order.getStatus() == OrderStatus.PENDING || order.getStatus() == OrderStatus.PROCESSING)) { // Updated status checks
            order.setStatus(OrderStatus.CANCELED); // Updated method name
            orderDAO.update(order);
            return true;
        }
        return false;
    }

    
    public void updateOrderStatus(int orderId, String statusParam) { // Updated method name
        Order order = orderDAO.findById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.valueOf(statusParam)); // Updated method name
            orderDAO.update(order);
        }
    }

    
    public List<Order> getAllOrdersOrderedByLatest() { // Updated method name
        return orderDAO.getAllOrdersOrderedByLatest(); // Updated method name
    }

    
    public List<Order> findOrderBySearch(Integer id, String status) { // Updated method name
        return orderDAO.findByIdOrStatus(id, status); // Updated method name
    }

    
    public Order findLastOrderByClientId(int clientId) { // Updated method name
        return orderDAO.findLastOrderByClientId(clientId); // Updated method name
    }

    
    public Order saveOrder(Order order) { // Updated method name
        return orderDAO.saveOrder(order); // Updated method name
        
    }
}
