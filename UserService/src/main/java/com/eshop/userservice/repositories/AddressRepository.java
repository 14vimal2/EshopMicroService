package com.eshop.userservice.repositories;


import com.eshop.userservice.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUserId(Long userId);

}
