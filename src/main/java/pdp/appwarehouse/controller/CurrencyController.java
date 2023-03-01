package pdp.appwarehouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.appwarehouse.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;
}
