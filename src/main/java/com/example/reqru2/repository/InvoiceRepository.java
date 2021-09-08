package com.example.reqru2.repository;

import com.example.reqru2.dto.InvoiceDto;
import com.example.reqru2.model.InvoiceInDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceInDb, Long> {
     List<InvoiceInDb> findAllByUserId(Long id);
}
