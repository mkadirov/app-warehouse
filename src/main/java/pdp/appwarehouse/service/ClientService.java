package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Client;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    //POST method to add new Client
    public Result addClient(Client client){
        if(clientRepository.existsByPhoneNumber(client.getPhoneNumber())){
            return new Result("PhoneNumber exists", false);
        }
        clientRepository.save(client);
        return new Result("Successfully added", true);
    }
    //GET method to get list of Client
    public Result getClientList(){
        List<Client> clientList = clientRepository.findAll();
        return new Result("Successfully retrieved", true, clientList);
    }

    //GET method to get a client by ID
    public Result getClientById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(supplier
                -> new Result("Successfully retrieved", true, supplier)).orElseGet(()
                -> new Result("Client not found", false));
    }

    //PUT method to upload a client by ID
    public Result updateClient(Integer id, Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()) {
            Client updatedClient = optionalClient.get();
            if(!updatedClient.getPhoneNumber().equals(client.getPhoneNumber())){
                if(clientRepository.existsByPhoneNumber(client.getPhoneNumber())){
                    return new Result("Phone number exists", false);
                }updatedClient.setPhoneNumber(client.getPhoneNumber());
            }
            updatedClient.setName(client.getPhoneNumber());
            clientRepository.save(updatedClient);
            return new Result("Successfully uploaded", true);
        }
        return new Result("Client not found", false);
    }

    //DELETE method to delete supplier by ID
    public Result deleteClientById(Integer id){
        if(clientRepository.existsById(id)){
            clientRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }
        return new Result("Client not found", false);
    }
}
