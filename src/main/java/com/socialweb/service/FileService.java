
package com.socialweb.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
/**
 * Class is responsible for saving and loading files to the server.
 * The class uses a property of (path.avatar) project configuration. 
 */
public interface FileService {
    /**
     * preserves the user's avatar to the server
     * @param avatar users photos
     * @return File name that will be available photos of the user. File is in a directory server ROOT.
     */
    //TODO What type of avatar?
    public String saveAvatar(MultipartFile avatar) throws IOException, IllegalStateException;
}
