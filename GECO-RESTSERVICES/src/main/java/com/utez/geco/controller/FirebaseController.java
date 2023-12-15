package com.utez.geco.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;

@CrossOrigin(
        origins = "http://52.1.80.209:3000",
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST},
        allowedHeaders = {HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION},
        exposedHeaders = {HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION}
)
@RestController
@RequestMapping(value = "/api/image-upload",method = RequestMethod.OPTIONS)
public class FirebaseController {
    private final String FIREBASE_FILE = "geco-firebase.json";
    private HashMap<String, Object> response;

    @PostMapping("")
    public ResponseEntity<?> uploadImageIncidence(@RequestParam("image")MultipartFile image) {
        response = new HashMap<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FIREBASE_FILE);
            Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(inputStream)).build().getService();

            String fileName = "incidences/" + image.getOriginalFilename();

            BlobId blobId = BlobId.of("gs://geco-99573.appspot.com", fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(image.getContentType()).build();
            Blob blob = storage.create(blobInfo, image.getBytes());

            response.put("status", HttpStatus.OK);
            response.put("message", "Imagen almacenada con exito");
            response.put("imageUrl", blob.getMediaLink());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("message", "No se creó pudo mandar la imágen");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/hotel")
    public ResponseEntity<?> uploadHotelLogo(@RequestParam("image") MultipartFile image) {
        response = new HashMap<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FIREBASE_FILE);
            Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(inputStream)).build().getService();

            String fileName = "logos/" + image.getOriginalFilename();

            BlobId blobId = BlobId.of("geco-99573.appspot.com", fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(image.getContentType()).build();
            Blob blob = storage.create(blobInfo, image.getBytes());

            response.put("status", HttpStatus.OK);
            response.put("message", "Imagen almacenada con exito");
            response.put("imageUrl", blob.getMediaLink());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("message", "No se creó pudo mandar la imágen");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
