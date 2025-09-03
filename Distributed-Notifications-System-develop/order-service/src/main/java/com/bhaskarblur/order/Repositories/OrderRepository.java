package com.bhaskarblur.order.Repositories;

import com.bhaskarblur.order.Models.OrderModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderModel, String> {

    // Create an order (inserts are handled by MongoRepository's save method)
    default OrderModel createOrder(OrderModel orderModel) {
        return save(orderModel);  // Saves and returns the created order
    }

    // Fetch orders by userId
    List<OrderModel> findByUserId(@NotNull String userId);
}
