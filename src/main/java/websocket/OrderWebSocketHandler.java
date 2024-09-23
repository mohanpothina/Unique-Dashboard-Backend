package websocket;


import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


//this is the handler that manages WebSocket connections for order updates.
//@Component
//public class OrderWebSocketHandler extends TextWebSocketHandler {
//
//    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) {
//        sessions.add(session);
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//        sessions.remove(session);
//    }
//
//    // This is the method we will call from the service to send the order
//    public void sendOrder(Order order) throws IOException {
//        String orderJson = objectMapper.writeValueAsString(order);
//        synchronized (sessions) {
//            for (WebSocketSession session : sessions) {
//                if (session.isOpen()) {
//                    session.sendMessage(new TextMessage(orderJson));
//                }
//            }
//        }
//    }
//}

@Component
public class OrderWebSocketHandler {

    @Autowired
    private WebSocketController webSocketController;

    @EventListener
    public void handleOrderEvent(Order order) {
        webSocketController.sendOrder(order);
    }
}

