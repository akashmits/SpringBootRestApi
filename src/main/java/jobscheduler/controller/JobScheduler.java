package jobscheduler.controller;

import jobscheduler.pojo.JobPojo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler/v1/job/")
public class JobScheduler {

    @PostMapping(value = "submit")
    public ResponseEntity<String> submitJob(@RequestBody JobPojo jobPojo){

        return null;
    }

    @GetMapping(value = "callback")
    public ResponseEntity<String> getStatus(@RequestParam(required = true) String jobId){
        return null;
    }

    @GetMapping(value = "callback")
    public ResponseEntity<String> updateStatus(@RequestParam(required = true) String jobId,@RequestParam (required = true)String status){
        return null;
    }



}
