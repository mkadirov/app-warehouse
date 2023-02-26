package pdp.appwarehouse.service;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.appwarehouse.entity.Attachment;
import pdp.appwarehouse.entity.AttachmentContent;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.AttachmentContentRepository;
import pdp.appwarehouse.repository.AttachmentRepository;


import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    //POST method to add new Attachment(files)
    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        String idList="";  // as result to send Ids of Files

        Iterator<String> fileNames = request.getFileNames(); // take file Names from list of Files
        while (fileNames.hasNext()){
            MultipartFile file = request.getFile(fileNames.next());    // take next file from  list of Files
            Attachment attachment;
            if (file != null) {
                attachment = new Attachment(null, file.getOriginalFilename(),         // create new attachment
                        file.getSize(), file.getContentType());
                Attachment savedAttachment = attachmentRepository.save(attachment); // save attachment to DB
                AttachmentContent attachmentContent = new AttachmentContent(null, file.getBytes(), savedAttachment);
                attachmentContentRepository.save(attachmentContent); // save main Content to DB
                idList += savedAttachment.getId() + " ";
            }
        }
        return new Result("Successfully added", true, idList);
    }

    //GET method to get attachment by ID
    public void getFileById(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
            if (optionalAttachmentContent.isPresent()){
                AttachmentContent content = optionalAttachmentContent.get();
                response.setHeader("Content-Disposition", "attachment; filename=\"" +
                        attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(content.getBytes(), response.getOutputStream());
            }
        }
    }

    //PUT method to update File
    public Result updateFile(Integer id, MultipartHttpServletRequest request) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Iterator<String> fileNames = request.getFileNames();
            MultipartFile file = request.getFile(fileNames.next());
            Attachment attachment = optionalAttachment.get();
            if(file!=null){
                attachment.setName(file.getOriginalFilename());
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachmentRepository.save(attachment);
                Optional<AttachmentContent> opAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
                AttachmentContent attachmentContent;
                if (opAttachmentContent.isPresent()){
                    attachmentContent= opAttachmentContent.get();
                    attachmentContent.setAttachment(attachment);
                    attachmentContent.setBytes(file.getBytes());
                    attachmentContentRepository.save(attachmentContent);
                }
                attachmentContent = new AttachmentContent(null, file.getBytes(), attachment);
                attachmentContentRepository.save(attachmentContent);
            }
            return new Result("File is empty", false);
        }
        return new Result("File not found", false);
    }

    //Delete method to delete File
    public Result deleteFileById(Integer id){
        if(attachmentRepository.existsById(id)){
            attachmentRepository.deleteById(id);
            Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
            if (byAttachmentId.isPresent()) {
                AttachmentContent attachmentContent = byAttachmentId.get();
                attachmentContentRepository.delete(attachmentContent);
            }
            return new Result("Successfully deleted", true);
        }
        return new Result("File not found", false);
    }
}
