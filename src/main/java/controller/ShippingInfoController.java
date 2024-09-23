package controller;

import model.ShippingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ShippingInfoService;

import java.util.List;

@RestController
@RequestMapping("/shippings")
@CrossOrigin(origins = "http://localhost:3000")
public class ShippingInfoController {

    @Autowired
    private ShippingInfoService shippingInfoService;


    @GetMapping
    public ResponseEntity<List<ShippingInfo>> getAllShippingInfo() {
        List<ShippingInfo> shippings = shippingInfoService.getAllShippingInfo();
        return new ResponseEntity<>(shippings, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ShippingInfo> addShippingInfo(@RequestBody ShippingInfo shippingInfo) {
        ShippingInfo createdShipping = shippingInfoService.addShippingInfo(shippingInfo);
        return new ResponseEntity<>(createdShipping, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShippingInfo> updateShippingInfo(@PathVariable String id, @RequestBody ShippingInfo shippingInfo ) {
        ShippingInfo updatedShipping = shippingInfoService.updateShippingInfo(id, shippingInfo);
        return new ResponseEntity<>(updatedShipping, HttpStatus.OK);
    }

    // Delete Shipping Info
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingInfo(@PathVariable String id) {
        shippingInfoService.deleteShippingInfo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
