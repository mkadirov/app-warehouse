package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Measurement;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    // POST method to add new Measurement
    public Result addMeasurementService(Measurement measurement){

        Result result = new Result();
        // Check if exists Measurement Name
        if(measurementRepository.existsByName(measurement.getName())){
            result.setMessage("Measurement exists");
            result.setSuccess(false);
        }else {
            Measurement newMeasurement = new Measurement();
            newMeasurement.setName(measurement.getName());
            measurementRepository.save(newMeasurement);
            result.setSuccess(true);
            result.setMessage("Successfully added");
        }
        return result;
    }

    // GET method to get List of Measurement
    public Result getMeasurementList(){
        return new Result("Successfully retrieved", true, measurementRepository.findAll() );
    }

    // GET method to get a measurement by ID
    public Result getMeasurementByID(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.map(measurement
                -> new Result("Successfully retrieved", true, measurement)).orElseGet(()
                -> new Result("Measurement not found", false));
    }

    //PUT method to update a measurement by ID
    public Result updateMeasurement(Integer id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()){
            Measurement updatedMeasurement = optionalMeasurement.get();
            if(!updatedMeasurement.getName().equals(measurement.getName())){
                if (measurementRepository.existsByName(measurement.getName())){
                    return new Result("Measurement exists", false);
                }
                updatedMeasurement.setName(measurement.getName());
            }
            return new Result("Successfully updated", true);
        }
        return new Result("Measurement not found", false);
    }


    // DELETE method to delete Measurement byID
    public Result deleteMeasurementById(Integer id){
        if(measurementRepository.existsById(id)){
            measurementRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }
        return  new Result("Measurement not found", false);
    }
}
