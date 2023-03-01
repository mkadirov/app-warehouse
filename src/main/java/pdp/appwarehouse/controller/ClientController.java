package pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.appwarehouse.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;
}
