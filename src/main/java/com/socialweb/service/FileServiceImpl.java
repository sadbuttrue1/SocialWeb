package com.socialweb.service;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import static com.socialweb.utility.Help.*;

@Component
public class FileServiceImpl implements FileService {

    @Override    
    public String saveAvatar(MultipartFile avatar) throws IOException {        
        String nameAvatar = nameAvatar(avatar.getOriginalFilename());        
        File savedFile = new File(PATH_ROOT + nameAvatar);
        avatar.transferTo(savedFile);        
        return nameAvatar;
    }
}
