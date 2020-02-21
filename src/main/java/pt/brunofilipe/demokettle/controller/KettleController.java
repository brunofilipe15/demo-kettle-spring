package pt.brunofilipe.demokettle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.brunofilipe.demokettle.service.KettleService;

@RestController
@RequestMapping("kettle")
public class KettleController {

    @Autowired
    private KettleService kettleService;

    @GetMapping
    public ResponseEntity get(){
        kettleService.executeAsynchronouslyJobKettle();
        System.out.println("-> Reponse");
        return ResponseEntity.ok("Ok!");
    }
}
