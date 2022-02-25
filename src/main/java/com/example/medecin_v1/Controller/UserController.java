package com.example.medecin_v1.Controller;

import com.example.medecin_v1.entity.Document;
import com.example.medecin_v1.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private DocumentRepository documentRepository;


    //upload any file
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Document uploadFile(@RequestParam("document") MultipartFile multipartFile) throws IOException {
        String filename= StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        Document doc =new Document();
        doc.setName(filename);
        doc.setContent(multipartFile.getBytes());
        doc.setSize(multipartFile.getSize());
        doc.setUploadTime(new Date());

        documentRepository.save(doc);

        return doc;
    }
    //get all files
    @GetMapping("/files")
    public List<Document> GetFiles(){

        return documentRepository.findAll();

    }
    //Download Files
    @GetMapping("/download")
    public void downloadFile(@RequestParam("FileId")Long id, HttpServletResponse response ) throws Exception {
        Optional<Document> result =documentRepository.findById(id);
        if(!result.isPresent()){
            throw new Exception("Could Not found document by Id :"+id);
        }
        Document document=result.get();
        response.setContentType("application/octet-stream");
        String headerKey ="Content-Disposition";
        String headerValue="attachment;filename="+document.getName();
        response.setHeader(headerKey,headerValue);
        ServletOutputStream outputStream=response.getOutputStream();
        outputStream.write(document.getContent());
        outputStream.close();

    }

}
