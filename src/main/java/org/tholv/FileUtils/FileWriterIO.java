package org.tholv.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileWriterIO implements Serializable {
    File file;
    private String path;
    private Charset charsets;
    private String charset;

    public FileWriterIO(String path, Charset charsets) {
        this.path = path;
        this.charsets = charsets;
        file=new File(path,charset);
    }
    public FileWriterIO(String path, String charset) {
        this.path = path;
        this.charset = charset;
        file=new File(path,charset);
    }
    public FileWriterIO(String path){
        this.path=path;
        charsets=StandardCharsets.UTF_8;
        file=new File(path);
    }
    public boolean isExist(){
        return file.exists();
    }
    public boolean isEmptySize(){
        return getSize()==0;
    }
    public boolean isOpening(){
        FileSystem fs=file.toPath().getFileSystem();
        return fs.isOpen();
    }
    public synchronized void write(byte[] buffer)throws Exception{
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(buffer);
    }
    public synchronized void write(String buffer)throws Exception{
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(buffer.getBytes());
    }
    public synchronized boolean createNewFile()throws Exception{
        if(file.exists()) return file.createNewFile();
        throw new IllegalArgumentException("File is exist");
    }
    public synchronized boolean deleteFile()throws Exception{
        if(file.exists()) return file.delete();
        throw  new IllegalArgumentException("No such file or directory");
    }
    public long getSize(){
        return file.length();
    }
    public String getAbsolutePath(){
        return file.getAbsolutePath();
    }
    public boolean mkdir(){
       return file.mkdir();
    }
    public synchronized void insertInLineNotReplace(String insert,int line)throws Exception{
        if(insert==null||insert.isBlank()||insert.isEmpty())throw new IllegalArgumentException("line cannot null");
        if(line<=0)throw new IllegalArgumentException("line mustn't be low 0");
        int i=0;
        Map<Integer,String> mapLine=new HashMap<>();
        Scanner sc =new Scanner(new FileInputStream(file));
        String str="";
        while(sc.hasNext()){
            String temporary=sc.nextLine();
            str+=temporary+"\n";
            mapLine.put(i,temporary+"\n");
            i++;
        }
        if(line>i){
            FileWriterIO fr=new FileWriterIO(path);
            fr.deleteFile();
            fr.createNewFile();
            int space=line-i;
            String spacing="";
            for(int j=0;j<space;j++)spacing+="\n";
            fr.write(str.concat(spacing).concat(insert));
        }else{
            FileWriterIO fr=new FileWriterIO(path);
            fr.deleteFile();
            fr.createNewFile();
            int space=line;
            Scanner tempScanner=new Scanner(str);
            String tempStr="";
            int k=1;
            while (tempScanner.hasNext()){
                tempStr+=tempScanner.nextLine()+"\n";
                k++;
                if(k>line-1)break;
            }
            tempStr+=insert;
            for(int m=space+1;m<i;m++){
                tempStr+=mapLine.get(m);
                if(mapLine.get(m)==null)tempStr+="";
            }
            System.out.println(space+"and"+i);
            fr.write(tempStr);
        }

    }
    public synchronized void insertInLineReplace(String insert,int line)throws Exception{
        if(insert==null||insert.isBlank()||insert.isEmpty())throw new IllegalArgumentException("line cannot null");
        if(line<=0)throw new IllegalArgumentException("line mustn't be low 0");
        int i=0;
        Map<Integer,String> mapLine=new HashMap<>();
        Scanner sc =new Scanner(new FileInputStream(file));
        String str="";
        while(sc.hasNext()){
            String temporary=sc.nextLine();
            str+=temporary+"\n";
            mapLine.put(i,temporary+"\n");
            i++;
        }
        if(line>i){
            FileWriterIO fr=new FileWriterIO(path);
            fr.deleteFile();
            fr.createNewFile();
            int space=line-i;
            String spacing="";
            for(int j=0;j<space;j++)spacing+="\n";
            fr.write(str.concat(spacing).concat(insert));
        }else{
            FileWriterIO fr=new FileWriterIO(path);
            fr.deleteFile();
            fr.createNewFile();
            int space=line;
            Scanner tempScanner=new Scanner(str);
            String tempStr="";
            int k=1;
            while (tempScanner.hasNext()){
                tempStr+=tempScanner.nextLine()+"\n";
                k++;
                if(k>line-1)break;
            }
            tempStr+=insert+"\n";
            for(int m=space;m<i;m++){
                tempStr+=mapLine.get(m);
                if(mapLine.get(m)==null)tempStr+="";
            }
            fr.write(tempStr);
            System.out.println(tempStr);
        }

    }
    public static void main(String[] args) throws Exception{
    FileWriterIO fi=new FileWriterIO("C:\\Users\\ADMIN\\Desktop\\New folder\\demo.txt");
    fi.insertInLineReplace("tholv",13);

    }
}
