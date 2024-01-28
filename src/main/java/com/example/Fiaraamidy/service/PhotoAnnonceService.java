package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.PhotoAnnonce;
import com.example.Fiaraamidy.model.UploadPhoto;
import com.example.Fiaraamidy.repository.PhotoAnnonceRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoAnnonceService {

    private PhotoAnnonceRepository photoAnnonceRepository;

    public PhotoAnnonceService(PhotoAnnonceRepository photoAnnonceRepository) {
        this.photoAnnonceRepository = photoAnnonceRepository;
    }

    public void ajouterImage(int id,File file) throws Exception{
        System.out.println(1);
        PhotoAnnonce photoAnnonce = this.compressionImage(file,515,386,0.5);
        System.out.println(2);

        String base64 = this.imageToBase64(photoAnnonce.getBin());
        System.out.println(3);

        photoAnnonce.setIdAnnonce(id);
        System.out.println(4);

        photoAnnonce.setBin(base64);

        System.out.println(5);
        this.photoAnnonceRepository.save(photoAnnonce);
    }

//    public void ajouterImage(UploadPhoto uploadPhoto) throws Exception{
//        System.out.println(1);
//        PhotoAnnonce photoAnnonce = this.compressionImage(uploadPhoto.getPath(),515,386,0.5);
//        System.out.println(2);
//
//        String base64 = this.imageToBase64(photoAnnonce.getBin());
//        System.out.println(3);
//
//        photoAnnonce.setIdAnnonce(uploadPhoto.getIdAnnonce());
//        System.out.println(4);
//
//        photoAnnonce.setBin(base64);
//
//        System.out.println(5);
//        this.photoAnnonceRepository.save(photoAnnonce);
//    }

    public  PhotoAnnonce compressionImage(File file, int width, int height, double reduction) throws Exception {

        long tailleAvant = file.length();

        Thumbnails.of(file)
                .size(width, height)
                .outputQuality(reduction) // varie de (0.0 - 1.0)
                .toFile(file);

        int tailleApres = (int) file.length();
//        System.out.println("Taille de l'image avant la compression : " + tailleAvant + " octets");
//        System.out.println("Taille de l'image après la compression : " + tailleApres + " octets");
        System.out.println("Image compressée avec succès !");
        PhotoAnnonce photo = new PhotoAnnonce(0, file.getName(),tailleApres,".JPEG",file.getPath());

        return photo;
    }

//    public  PhotoAnnonce compressionImage(String path, int width, int height, double reduction) throws Exception {
//        path = getPath(path);
//        File inputFile = new File(path);
//        String newPath = "C://Users//public//"+inputFile.getName();
//        File outputFile = new File(path);
//
//        long tailleAvant = inputFile.length();
//
//
//        Thumbnails.of(inputFile)
//                .size(width, height)
//                .outputQuality(reduction) // varie de (0.0 - 1.0)
//                .toFile(outputFile);
//
//
//        int tailleApres = (int) outputFile.length();
//
//
//
//        System.out.println("Taille de l'image avant la compression : " + tailleAvant + " octets");
//        System.out.println("Taille de l'image après la compression : " + tailleApres + " octets");
//
//        System.out.println("Image compressée avec succès !");
//        PhotoAnnonce photo = new PhotoAnnonce(0,inputFile.getName(),tailleApres,".JPEG",path);
//
//        return photo;
//    }

    public static String imageToBase64(String imagePath) throws Exception {
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {

            byte[] imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);


            return Base64.getEncoder().encodeToString(imageData);
        }
    }


    public  List<PhotoAnnonce> getPhotoByIdAnnonce(int idAnnonce){
        List<PhotoAnnonce> optionalPhotoAnnonces = this.photoAnnonceRepository.findPhotoAnnonceByIdAnnonce(idAnnonce);
        return optionalPhotoAnnonces;
    }

    public  List<PhotoAnnonce> getAllPhoto(){
        List<PhotoAnnonce> optionalPhotoAnnonces = this.photoAnnonceRepository.findAll();
        return optionalPhotoAnnonces;
    }


    public String getPath(String path){
        String[] all = path.split("/app/");
        path = all[1];
        return path;
    }
}
