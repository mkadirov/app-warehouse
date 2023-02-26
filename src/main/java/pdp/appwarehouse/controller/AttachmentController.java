package pdp.appwarehouse.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.uploadFile(request);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFileById(id, response);
    }

    @PutMapping("/update/{id}")
    public Result updateFile(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {
        return attachmentService.updateFile(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteFileById(@PathVariable Integer id){
        return attachmentService.deleteFileById(id);
    }
}
