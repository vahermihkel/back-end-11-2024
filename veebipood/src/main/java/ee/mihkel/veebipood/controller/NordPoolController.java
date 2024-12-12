package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.models.TimestampPrice;
import ee.mihkel.veebipood.service.NordPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NordPoolController {

    @Autowired
    NordPoolService nordPoolService;

    // localhost:8080/nord-pool?country=ee
    @GetMapping("nord-pool")
    public List<TimestampPrice> getElectricityPrices(@RequestParam String country) {
        return nordPoolService.getElectricityPrices(country);
    }
}
