package service;

import exception.ResourceNotFoundException;
import model.ShippingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ShippingInfoRepository;

import java.util.List;



@Service
public class ShippingInfoService {

    @Autowired
    private ShippingInfoRepository shippingInfoRepository;

    public List<ShippingInfo> getAllShippingInfo() {
        return shippingInfoRepository.findAll();
    }

    public ShippingInfo addShippingInfo(ShippingInfo shippingInfo) {
        return shippingInfoRepository.save(shippingInfo);
    }

    public ShippingInfo updateShippingInfo(String id, ShippingInfo shippingDetails) {
        ShippingInfo shippingInfo = shippingInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShippingInfo not found with id " + id));

        shippingInfo.setOrderId(shippingDetails.getOrderId());
        shippingInfo.setFullName(shippingDetails.getFullName());
        shippingInfo.setPhoneNumber(shippingDetails.getPhoneNumber());
        shippingInfo.setEmail(shippingDetails.getEmail());
        shippingInfo.setAddress(shippingDetails.getAddress());
        shippingInfo.setCity(shippingDetails.getCity());
        shippingInfo.setPostalCode(shippingDetails.getPostalCode());
        shippingInfo.setCountry(shippingDetails.getCountry());
        shippingInfo.setStatus(shippingDetails.getStatus());

        return shippingInfoRepository.save(shippingInfo);
    }

    public void deleteShippingInfo(String id) {
        ShippingInfo shippingInfo = shippingInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShippingInfo not found with id " + id));
        shippingInfoRepository.delete(shippingInfo);
    }
}




//@Service
//public class ShippingInfoService {
//
//    @Autowired
//    private ShippingInfoRepository shippingInfoRepository;
//
//    public List<ShippingInfo> getAllShippingInfo() {
//        return shippingInfoRepository.findAll();
//    }
//
//    public ShippingInfo addShippingInfo(ShippingInfo shippingInfo) {
//        return shippingInfoRepository.save(shippingInfo);
//    }
//}
