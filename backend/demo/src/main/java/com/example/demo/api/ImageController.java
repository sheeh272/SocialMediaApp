package com.example.demo.api;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("api/v1/image")
@RestController
public class ImageController {
    @Autowired
    private HttpServletRequest request;

    @PostMapping
    void uploadImage(@RequestParam("image") MultipartFile file )  throws IOException
    {
        //System.out.println("Number of bytes =" + file.getBytes());
        //Gets username from JWT using SecurityContextHolder which is set in JWTRequestFilter.  This will be name of image
        String pathName = "./src/main/java/com/example/demo/imageStorage/".concat(SecurityContextHolder.getContext().getAuthentication().getName());
        Path path = Paths.get(pathName);

        file.transferTo(new File(path.toAbsolutePath().toString()));
    }

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        String pathName = "./src/main/java/com/example/demo/imageStorage/".concat(SecurityContextHolder.getContext().getAuthentication().getName());
        //System.out.println(pathName);
        InputStream in = new FileInputStream(pathName);
        return IOUtils.toByteArray(in);
    }

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE,
            params = {"name",}
    )
    public @ResponseBody byte[] getProfilePictureFromId(@RequestParam("name") String name) throws IOException {
        String pathName = "./src/main/java/com/example/demo/imageStorage/".concat(name);
        //System.out.println(pathName);
        InputStream in = new FileInputStream(pathName);
        return IOUtils.toByteArray(in);
    }

}


