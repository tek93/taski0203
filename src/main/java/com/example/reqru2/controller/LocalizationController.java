package com.example.reqru2.controller;

import com.example.reqru2.model.Localization;
import com.example.reqru2.repository.LocalizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController

public class LocalizationController {

    @Autowired
    private LocalizationRepository localizationRepository;

    @PostMapping("/api/localization/save")
    public Localization saveLocalization(@RequestBody  @Valid  Localization localization){
       return localizationRepository.save(localization);
    }
    @GetMapping("/api/localization/all")
    public List<Localization>localizationList(){
        return localizationRepository.findAll();
    }
    @GetMapping("/api/localization/{id}")
    public Optional<Localization> findById(@PathVariable  Long id){
        return localizationRepository.findById(id);
    }
    @GetMapping("/api/localization/device{deviceId}")
    public List<Localization> localizationList(@PathVariable  Long deviceId){
       return localizationRepository.findAllByDeviceId(deviceId);
    }

}
