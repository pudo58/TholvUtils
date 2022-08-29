package org.tholv.FileUtils;
import org.tholv.SecurityUtils.Hash;
import org.tholv.Utils.DateUtils;
import org.tholv.Utils.DoubleUtils;
import org.tholv.Utils.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.util.*;
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
    public static FileWriterIO getInstance(String path, Charset charsets){
        return new FileWriterIO(path,charsets);
    }
    public static FileWriterIO getInstance(String path, String charsets){
        return new FileWriterIO(path,charsets);
    }
    public static FileWriterIO getInstance(String path){
        return new FileWriterIO(path);
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
    public String getName(){
        return file.getName();
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
    public void closeFile() throws IOException {
        FileSystem fs=file.toPath().getFileSystem();
        fs.close();
    }
    public synchronized void write(byte[] buffer)throws Exception{
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(buffer);
    }
    public static void saveImageToPath(String pathImage,String pathSaveTo) throws IOException {
        if(System.getProperty("os.name").contains("Window")&& pathSaveTo.charAt(pathSaveTo.length()-1)!='\\'){
            pathSaveTo+="\\";
        }
        if(System.getProperty("os.name").contains("Linux")&& pathSaveTo.charAt(pathSaveTo.length()-1)!='\\'){
            pathSaveTo+="\\";
        }
        if(!FileWriterIO.getInstance(pathImage).checkContentImage())throw new IllegalArgumentException("The picture is not in the correct format");
        FileWriterIO fw=FileWriterIO.getInstance(pathImage);
        FileWriterIO f=new FileWriterIO(pathSaveTo);
        if(!f.isExist())f.mkdir();
        String fileNameOutput=pathSaveTo+DateUtils.getInstance("yyyyMMdd").toString(new Date())+"_"+ Hash.MD5(fw.getName()).substring(0,10)+".png";
        if(pathImage.isEmpty()||pathImage.isBlank()||pathImage.length()==0)throw new IllegalArgumentException("Path is required");
        if(pathSaveTo.isEmpty()||pathSaveTo.isBlank()||pathSaveTo.length()==0)throw new IllegalArgumentException("Path is required");
        if(!fw.isExist())throw new IllegalArgumentException("File or path is not exist");
        BufferedImage bf= ImageIO.read(new File(fw.getAbsolutePath()));
        ImageIO.write(bf,"png",new File(fileNameOutput));
    }
    public synchronized void writeExcelReportFile(String reportName,List<String> columnName,List<String[]> values,Date date,String reporter){
        String rpt=StringUtils.upperCaseFirstWord(reporter).trim();
        String name=rpt.substring(rpt.lastIndexOf(" "),rpt.length());
        System.out.println(name);
        System.out.println(rpt);
        reportName.toUpperCase();
        columnName.add(0,"STT");
        String colspanGlobal=columnName.size()*2+"";
        String colspan=DoubleUtils.convertToDouble(colspanGlobal)*3/4+"";
        try {
            if(!file.exists())createNewFile();
            if(file.getName().contains(".xlsx"))throw new IllegalArgumentException("xlsx is not supported");
            else if(file.getName().contains(".xls")){
                String result="<html xmlns:o=\"urn:schemas-microsoft-com:office:office\" " +
                        "xmlns:x=\"urn:schemas-microsoft-com:office:excel\" " +
                        "xmlns=\"http://www.w3.org/TR/REC-html40\"><head><!--[if gte mso 9]>" +
                        "<xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>" +
                        "rpt_foreign_prepaid_control_new.xls</x:Name><x:WorksheetOptions>" +
                        "<x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet>" +
                        "</x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]-->" +
                        "<meta http-equiv=\"content-type\" content=\"text/plain;" +
                        " charset=UTF-8\"/><style>table tr th {mso-number-format:\\@;}</style></head><body><table>";
                result+="<tr><th align='center' colspan='"+colspanGlobal+"'>"+"CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM"+"</th></tr>";
                result+="<tr><th align='center' colspan='"+colspanGlobal+"'>"+"Độc lập - Tự do - Hạnh phúc"+"</th></tr>";
                result+="<tr><th align='center' colspan='"+colspanGlobal+"'>"+"___***___"+"</th></tr>";
                result+="<tr><th align='center' colspan='"+colspanGlobal+"'>"+reportName+"</th></tr>";
                result+="<tr><th align='center' colspan='"+colspanGlobal+"'>"+"Tháng "+ DateUtils.getMonthAndYear(date)+"</th></tr><tr><th colspan='"+colspanGlobal+"></th></tr>";
                result+="<tr><th colspan='"+colspanGlobal+"'></th></tr>";
                result+="<thead>";
                result+="<tr>";
                for(String s :columnName)result+="<th colspan='2' style=\"border: 0.5pt solid;\">"+s+"</th>";
                result+="</tr></thead>";
                result+="<tbody>";
                int i=1;
                for(String [] s :values){
                    result+="<tr>";
                    result+="<td colspan='2' align=\"center\" style=\"border: 0.5pt solid;\">" +i+"</td>";
                    for (String y:s){
                        result+="<td colspan='2' align=\"center\" style=\"border: 0.5pt solid;\">" +y.toString() +"</td>";
                    }
                    result+="</tr>";
                    i++;
                }
                result+="<tr></tr>";
                result+="<tr><th align='right' colspan ='"+(DoubleUtils.convertToDouble(colspanGlobal)-1/2)+"'>Hà Nội,ngày "+date.getDate()+" tháng "+ date.getMonth()+" năm "+DateUtils.getYear(date)+"</th></tr>";
                result+="<tr><th colspan='"+colspan+"'></th><th align='right' colspan ='"+(DoubleUtils.convertToDouble(colspanGlobal)-DoubleUtils.convertToDouble(colspan))+"'>NGƯỜI LẬP BIÊN BẢN BÁO CÁO</th></tr>";
                result+="<tr><th colspan='"+colspan+"'></th><th align='center' colspan ='"+(DoubleUtils.convertToDouble(colspanGlobal)-DoubleUtils.convertToDouble(colspan))+"'>"+name+"</th></tr>";
                result+="<tr><th colspan='"+colspanGlobal+"'></th></tr><tr><th colspan='"+colspanGlobal+"'></th></tr><tr><th colspan='"+colspanGlobal+"'></th></tr>";
                result+="<tr><th colspan='"+colspan+"'></th><th align='center' colspan ='"+(DoubleUtils.convertToDouble(colspanGlobal)-DoubleUtils.convertToDouble(colspan))+"'>"+rpt+"</th></tr>";
                result+="</tbody></table></body></html>";
                write(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void writeExcelFileOnlyTable(List<String> columnName,List<String[]> values){
        try {
            columnName.add(0,"STT");
            if(!file.exists())createNewFile();
            if(file.getName().contains(".xlsx"))throw new IllegalArgumentException("xlsx is not supported");
            else if(file.getName().contains(".xls")){
                String result="<html xmlns:o=\"urn:schemas-microsoft-com:office:office\" " +
                        "xmlns:x=\"urn:schemas-microsoft-com:office:excel\" " +
                        "xmlns=\"http://www.w3.org/TR/REC-html40\"><head><!--[if gte mso 9]>" +
                        "<xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>" +
                        "rpt_foreign_prepaid_control_new.xls</x:Name><x:WorksheetOptions>" +
                        "<x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet>" +
                        "</x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]-->" +
                        "<meta http-equiv=\"content-type\" content=\"text/plain;" +
                        " charset=UTF-8\"/><style>table tr th {mso-number-format:\\@;}</style></head><body><table>";
                        result+="<thead><tr>";
                        for(String s :columnName)result+="<th rowspan='2' style=\"border: 0.5pt solid;\">"+s+"</th>";
                        result+="</tr></thead>";
                        result+="<tbody>";
                        int i=1;
                        for(String [] s :values){
                            result+="<tr>";
                            result+="<td align=\"center\" style=\"border: 0.5pt solid;\">" +i+"</td>";
                            for (String y:s){
                                result+="<td align=\"center\" style=\"border: 0.5pt solid;\">" +y.toString() +"</td>";
                            }

                            result+="</tr>";
                            i++;
                        }
                        result+="</tbody></table></body></html>";
                        write(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public synchronized void write(String buffer)throws Exception{
           FileOutputStream fos=new FileOutputStream(file);
           fos.write(buffer.getBytes());
    }

    /**
     * support file .jpg,.jpeg,.png,.gif,.webm,.tiff
     * @return true if image format true
     * @throws IOException
     */
    public synchronized boolean checkContentImage() throws IOException {
        String fileExtensions[]={"jpg,jpeg,png,gif,webm,tiff"};
        String check=path.substring(path.lastIndexOf(".")+1,path.length());
        for(String s:fileExtensions)if(!s.contains(check))throw new IllegalArgumentException("File name is invalid");
        DataInputStream ins = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        return ins.readInt()==0xFFD8FFE0||ins.readInt()==0x89504E47||
                ins.readInt()==0x47494638|| ins.readInt()==0xFFD8FFE2||
                ins.readInt()==0x49492A00||ins.readInt()==0x52494646;
    }
    public synchronized boolean createNewFile()throws Exception{
        if(!file.exists()) return file.createNewFile();
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
            tempStr+=insert+"\n";
            for(int m=space+1;m<i;m++){
                tempStr+=mapLine.get(m);
                if(mapLine.get(m)==null)tempStr+="";
            }
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
        }

    }
}
