package pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Currency;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    // GET method to take List of currency

    public Result getListCurrency(){
        return new Result("CurrencyList retrieved successfully", true, currencyRepository.findAll());
    }

    //PUT method to add new currency
    public Result addCurrencyService(Currency currency){

        if(currencyRepository.existsByName(currency.getName())){
            return new Result("Currency exists", false);
        }
        currencyRepository.save(currency);
        return new Result("Successfully added", true);
    }

    // GET method to retrieve a currency by ID
    public Result getCurrencyById(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.map(currency ->
                new Result("Currency retrieved successfully", true, currency)).orElseGet(()
                -> new Result("Currency not found with ID: " + id, false));
    }
    // PUT method to update an existing currency
    public Result updateCurrency(Integer id,  Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty()) {
            return new Result("Currency not found with ID: " + currency.getId(), false);
        }
        Currency updatedCurrency = optionalCurrency.get();
        updatedCurrency.setName(currency.getName());
        currencyRepository.save(updatedCurrency);
        return new Result("Currency updated successfully", true);
    }

    // DELETE method to delete a currency by ID
    public Result deleteCurrencyById(Integer id) {
        if (currencyRepository.existsById(id)) {
            currencyRepository.deleteById(id);
            return new Result("Currency deleted successfully", true);
        } else {
            return new Result( "Currency not found with ID: " + id, false);
        }
    }
}
