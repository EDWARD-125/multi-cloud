package com.carcafe.multicloud.repository;

import com.carcafe.multicloud.builder.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, Long> {
}
