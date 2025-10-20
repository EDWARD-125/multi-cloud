package com.carcafe.multicloud.service;

import com.carcafe.multicloud.builder.VirtualMachine;
import com.carcafe.multicloud.repository.VirtualMachineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VirtualMachineService {

    private final VirtualMachineRepository repository;

    public VirtualMachineService(VirtualMachineRepository repository) {
        this.repository = repository;
    }

    public VirtualMachine save(VirtualMachine vm) {
        return repository.save(vm);
    }

    public Optional<VirtualMachine> findById(Long id) {
        return repository.findById(id);
    }

    public List<VirtualMachine> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
