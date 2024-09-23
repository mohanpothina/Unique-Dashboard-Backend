package repository;

import model.ShippingInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingInfoRepository extends MongoRepository<ShippingInfo, String> {

}
