/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.PhotoUpload;

import org.primefaces.model.UploadedFile;

/**
 *
 * @author salim ahmed
 */
public interface ImgUpLoad
{
    public void uploadImg(String imgPath,String fileName,UploadedFile uploadFile);
}
