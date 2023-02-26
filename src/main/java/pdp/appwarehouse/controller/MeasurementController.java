package pdp.appwarehouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.entity.Measurement;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.MeasurementRepository;
import pdp.appwarehouse.service.MeasurementService;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){

        return measurementService.addMeasurementService(measurement);
    }

    @GetMapping
    public Result getMeasurementList(){
        return measurementService.getMeasurementList();
    }

    @GetMapping("/{id}")
    public Result getMeasurementById(@PathVariable Integer id){
        return measurementService.getMeasurementByID(id);
    }

    @PutMapping("/{id}")
    public Result updateMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.updateMeasurement(id, measurement);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable  Integer id){
        return measurementService.deleteMeasurementById(id);
    }
}
