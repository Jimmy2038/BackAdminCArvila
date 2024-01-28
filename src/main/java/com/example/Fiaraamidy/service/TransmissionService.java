package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.Transmission;
import com.example.Fiaraamidy.repository.TransmissionRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TransmissionService {
    private TransmissionRepository transmissionRepository;

    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public void cree(Transmission transmission){
        this.transmissionRepository.save(transmission);
    }

    public List<Transmission> getAll()
    {
        return this.transmissionRepository.findAll();
    }

    public Transmission getById(int Id)
    {
        Optional<Transmission> optionalTransmission = this.transmissionRepository.findById(Id);
        if (optionalTransmission.isPresent())
        {
            return optionalTransmission.get();
        }
        throw new RuntimeException("Transmission not fount");
    }

    public void update(int id, Transmission transmission)
    {
        Transmission transmissionBDD = this.getById(id);
        if(transmission.getIdTransmision() == transmissionBDD.getIdTransmision())
        {
            transmissionBDD.setNomTransmission(transmission.getNomTransmission());
            this.transmissionRepository.save(transmissionBDD);
        }
    }
}
