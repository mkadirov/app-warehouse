package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.*;
import pdp.appwarehouse.payload.InputDto;
import pdp.appwarehouse.payload.OutputDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.payload.UniqueNumberGenerator;
import pdp.appwarehouse.repository.ClientRepository;
import pdp.appwarehouse.repository.CurrencyRepository;
import pdp.appwarehouse.repository.OutputRepository;
import pdp.appwarehouse.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    //POST method to add new Output
    public Result addOutput(OutputDto outputDto){
        if(outputRepository.existsByFactureNumber(outputDto.getFactureNumber())){
            return new Result("FactureNumber exists", false);
        }
        Optional<Warehouse> opWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(opWarehouse.isEmpty()){
            return new Result("Warehouse not found", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()){
            return new Result("Client not found", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Currency not found", false);
        }

        Output output = new Output();
        output.setTimestamp(output.getTimestamp());
        output.setWarehouse(opWarehouse.get());
        output.setClientId(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(output.getFactureNumber());
        output.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
        outputRepository.save(output);
        return new Result("Successfully added", true);
    }

    //GET method to get OutputList
    public Result getOutList(){
        return new Result("Successfully retrieved", true, outputRepository.findAll());
    }

    //GET method to get output by ID
    public Result getOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.map(output ->
                new Result("Successfully retrieved", true, output)).orElseGet(() ->
                new Result("Output not found", false));
    }

    //PUT method to update Output by ID
    public Result uploadOutput(Integer id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()){
            return new Result("Input not found", false);
        }
        Output output = optionalOutput.get();
        if(!output.getFactureNumber().equals(outputDto.getFactureNumber())) {
            if (outputRepository.existsByFactureNumber(outputDto.getFactureNumber())) {
                return new Result("FactureNumber exists", false);
            }
            output.setFactureNumber(outputDto.getFactureNumber());
        }
        Optional<Warehouse> opWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(opWarehouse.isEmpty()){
            return new Result("Warehouse not found", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()){
            return new Result("Client not found", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Currency not found", false);
        }

        output.setTimestamp(output.getTimestamp());
        output.setWarehouse(opWarehouse.get());
        output.setClientId(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        outputRepository.save(output);
        return new Result("Successfully added", true);
    }

    //DELETE method to delete output by ID
    public Result deleteOutput(Integer id){
        if (outputRepository.existsById(id)){
            outputRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }
        return new Result("Output not found", false);
    }
}
