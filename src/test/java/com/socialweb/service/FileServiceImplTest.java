package com.socialweb.service;

import java.io.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import static com.socialweb.utility.Help.*;
import org.mockito.stubbing.Answer;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceImplTest {
    
    @InjectMocks
    private FileServiceImpl fileServiceImpl;
    
    public FileServiceImplTest() {
    }

    @Test
    public void testSaveAvatar() throws Exception {        
        final String fileOriginalName = "originalName.jpg";        
        MultipartFile multipartFile = mock(MultipartFile.class);
        
        when(multipartFile.getOriginalFilename()).thenReturn(fileOriginalName);        
        Mockito.doAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        }).when(multipartFile).transferTo(any(File.class));
        
        String nameAvatar = fileServiceImpl.saveAvatar(multipartFile);
        
        assertFalse(nameAvatar.isEmpty());
        verify(multipartFile).transferTo(new File(PATH_ROOT + nameAvatar));
    }
    
}
