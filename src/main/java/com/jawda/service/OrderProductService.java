package com.jawda.service;



import com.jawda.dao.implementation.OrderProductDAOImpl;
import com.jawda.model.Order; // Changed Command to Order
import com.jawda.model.OrderProduct;
import com.jawda.model.Product;
import com.jawda.util.PersistenceUtil;
import com.jawda.dao.implementation.ProductDAOImpl;
import java.util.List;

import javax.persistence.EntityManager;

public class OrderProductService  {
    private OrderProductDAOImpl orderProductDAO; // Renamed the DAO reference
    EntityManager em = PersistenceUtil.getEntityManager();
    private ProductService productService;
	   
    
    public OrderProductService() {
        this.orderProductDAO = new OrderProductDAOImpl(em); 
        this.productService = new ProductService(new ProductDAOImpl());
    }

    public void addProductToOrder(Product product, int quantity, Order order) { // Updated method name
       

       

        OrderProduct orderProduct = new OrderProduct(); 
        orderProduct.setOrder(order); 
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);
       

        orderProductDAO.addOrderProduct(orderProduct); // Updated method name
    }

    
    public List<OrderProduct> getCurrentCartForClient(int clientId, int orderId) { // Updated method name
        return orderProductDAO.findCurrentCartForClient(clientId, orderId); // Updated method name
    }

    public void updateCart(int orderId, List<OrderProduct> orderProducts) { // Updated method name
        orderProductDAO.deleteByOrderId(orderId); // Updated method name

        for (OrderProduct orderProduct : orderProducts) { // Updated class name
            orderProductDAO.addOrderProduct(orderProduct); // Updated method name
        }
    }

    public void confirmOrder(int orderId) { // Updated method name
        orderProductDAO.updateOrderStatusToValid(orderId); // Updated method name
    }
}
