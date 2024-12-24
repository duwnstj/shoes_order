package com.personal.common.component;

import com.personal.common.dto.FileUploadDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class FileManagement {

    public FileUploadDto storeFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();               // 사용자가 업로드한 파일이름
        String convertFileName = createStoreFileName(fileName);     // 서버에 저장하는 파일명(uu아이디+.+확장자명)
        Long fileSize = file.getSize();                                 // 파일 크기
        String contentType = file.getContentType();                     // 파일 contentType
        return new FileUploadDto(fileName, convertFileName , fileSize , contentType);
    }

    public String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString(); // 서버에 저장하는 파일명(uu아이디)
        return uuid + "." + ext;                    // ext : 확장자명
    }

    public String extractExt(String originalFilename) {        // 확장자명 꺼내기
        int pos = originalFilename.lastIndexOf(".");        // 위치를 가져온다.
        return originalFilename.substring(pos + 1);   // . 다음에 있는 확장자명 꺼냄
    }
}
