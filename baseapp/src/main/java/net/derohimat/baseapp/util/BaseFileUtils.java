package net.derohimat.baseapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Base64OutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by deni on 7/12/15.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("SdCardPath")
public class BaseFileUtils {

    public static String IMAGE_DIR = "/Pictures/BaseApp";
    public static final String CACHE_DIR = "/Cache";
    public static final String FILE_EXTENSION = "jpg";
    public static final String SDCARD1 = "/sdcard1";

    public static File getAppDir(Context context) {

        boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        File parent = isSDPresent ? Environment.getExternalStorageDirectory() : context.getFilesDir();

        if (!isSDPresent && isHaveSDCard1())
            parent = new File(SDCARD1);

        File dir = new File(parent, IMAGE_DIR);
        if (!dir.exists())
            dir.mkdirs();
        return dir;
    }

    public static File getCacheDir(Context context) {
        File appDir = getAppDir(context);
        File cacheDir = new File(appDir, CACHE_DIR);
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        return cacheDir;
    }

    public static boolean isHaveSDCard1() {
        File file = new File(SDCARD1);
        return file.exists();
    }

    public static File getFile(Context context, String name) {
        return getFile(context, name, FILE_EXTENSION);
    }

    public static File getFile(Context context, String name, String extension) {
        extension = ".".concat(extension.trim().toLowerCase(Locale.getDefault()));
        File dir = getAppDir(context);
        return new File(dir, name + extension);
    }

    public static File getTempFile(Context context) {
        return getFile(context, "temp");
    }

    public static File getTempVideoFile(Context context) {
        return getFile(context, "TempVids", ".mp4");
    }

    public static File getShowVideoFile(Context context) {
        return getFile(context, "ShowVids", ".mp4");
    }

    public static Uri getTempUri(Context context) {
        return Uri.fromFile(getTempFile(context));
    }

    public static void copyFile(File afile, File bfile) {
        InputStream inStream;
        OutputStream outStream;

        try {
            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

            inStream.close();
            outStream.close();

            Timber.i("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File base64ToFile(String data, File file) {
        byte[] datas = Base64.decode(data, Base64.DEFAULT);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(datas);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            Timber.e(e.getMessage());
        }
        return file;
    }

    public static File transferImageToFile(File destFile, Bitmap bitmap) {
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(destFile));
        } catch (FileNotFoundException e) {
            Timber.e(e.getMessage());
        }
        return destFile;
    }

    public static File transferFileToFile(File source, File destFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(destFile);
            BaseIoUtils.copyStream(fileInputStream, fileOutputStream, null);
        } catch (Exception e) {
            Timber.e(e.getMessage());
        } finally {
            if (fileInputStream != null)
                BaseIoUtils.closeSilently(fileInputStream);
            if (fileOutputStream != null)
                BaseIoUtils.closeSilently(fileOutputStream);
        }
        return destFile;
    }

    public static String fileToBase64String(File file) {
        try {
            long start = System.nanoTime();
            InputStream inputStream = new FileInputStream(file.getAbsolutePath());
            byte[] buffer = new byte[1024];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);
//			GZIPOutputStream gzip = new GZIPOutputStream(output64);
            try {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output64.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                Timber.e(e.getMessage());
            }
            output64.close();
            Timber.i("Took " + (System.nanoTime() - start) + " to convert to byte[]");

            return output.toString();
        } catch (Exception e) {
            Timber.e(e.getMessage());
        }
        return null;
    }

}
