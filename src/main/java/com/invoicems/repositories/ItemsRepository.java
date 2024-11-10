package com.invoicems.repositories;

import com.invoicems.models.Items;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Items, String> {
  
    // find by hsn
    Items findByHsn(String hsn);
    
    List<Items> findByDeletedFalse();

    //  specific item by name if not soft-deleted
    Optional<Items> findByItemNameAndDeletedFalse(String itemName);
}
