package service;


import exception.ResourceNotFoundException;
import model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCartByOrderId(String orderId) {
        return Optional.ofNullable(cartRepository.findByOrderId(orderId));
    }

    public List<Cart> getAllCarts(){
        return  cartRepository.findAll();
    }

    public Cart updateCart(String id, Cart cartDetails) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + id));

        cart.setOrderId(cartDetails.getOrderId());
        cart.setItems(cartDetails.getItems());

        return cartRepository.save(cart);
    }

    public void deleteCart(String id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + id));
        cartRepository.delete(cart);
    }

}