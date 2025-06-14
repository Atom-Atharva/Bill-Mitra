package in.billmitra.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public String uploadFile(MultipartFile file);

    public boolean deleteFile(String imgUrl);
}
