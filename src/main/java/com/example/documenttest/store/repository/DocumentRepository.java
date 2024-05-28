package com.example.documenttest.store.repository;

import com.example.documenttest.store.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Documents, UUID> {


    List<Documents> findByTypeOrNumberOrDate(String type, String number, Date dateFrom);
    Optional<Documents> findByTypeAndNumberAndDate(
            String type,
            String number,
            Date date);


}
