/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.PhotoUpload;

import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author salim ahmed
 */
public class ImgUpload_Impl implements Serializable,ImgUpLoad
{

    @Override
    public void uploadImg(String imgPath, String fileName,UploadedFile uploadFile)
    {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        String filepath = servletContext.getRealPath("/images/"+imgPath) + File.separator + fileName;

        try
        {
            FileOutputStream fos = new FileOutputStream(new File(filepath));

            InputStream is = uploadFile.getInputstream();

            int BUFFER_SIZE = 8192;

            byte[] buffer = new byte[BUFFER_SIZE];

            int a;

            while (true) {
                a = is.read(buffer);

                if (a < 0) {
                    break;
                }

                fos.write(buffer, 0, a);

                fos.flush();
            }

            fos.close();

            is.close();
        } 
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    
}
