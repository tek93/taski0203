package com.example.reqru2.repository;

import com.example.reqru2.model.Localization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LocalizationRepository extends JpaRepository<Localization, Long > {

    List<Localization> findAllByDeviceId(Long deviceId);
}
