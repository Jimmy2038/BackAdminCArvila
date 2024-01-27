package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.Marque;
import com.example.Fiaraamidy.entites.Model;
import com.example.Fiaraamidy.repository.ModelRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    private ModelRepository modelRepository;
    private MarqueService marqueService;

    public ModelService(ModelRepository modelRepository, MarqueService marqueService) {
        this.modelRepository = modelRepository;
        this.marqueService = marqueService;
    }

    public void creer(Model model){
        this.modelRepository.save(model);
    }

    public List<Model> getAll(){
        return this.modelRepository.findAll();
    }

    public List<Model> getAllByMarque(int id){
        Marque marque=this.marqueService.getById(id);
        return this.modelRepository.findModelByMarque(marque);
    }

    public Model getById(int id){
        Optional<Model> optionalModel = this.modelRepository.findById(id);
        if (optionalModel.isPresent()){
            return  optionalModel.get();
        }
        throw new RuntimeException("Model not fount");
    }
    public void update(int id, Model model){
        Model modelBdd = this.getById(id);
        if (modelBdd.getIdModel() == model.getIdModel()){
            modelBdd.setNomModel(model.getNomModel());

            this.modelRepository.save(modelBdd);
        }
    }
}
