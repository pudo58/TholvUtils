package org.tholv.Utils;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.HashMap;
public class DoubleUtils {
    public static final double PI = 3.141592653589793;
    public static final double E = 2.718281828459045;

    /**
     * @param value double <b>số muốn làm tròn</b>
     * @param places int <p>Format sau <b>places</b> dấu phẩy</p>
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public static String formatToMoney(String value){
        if(Double.parseDouble(value) < 0)throw new IllegalArgumentException("Value must be positive");
        return new DecimalFormat("#,###.00").format(Double.parseDouble(value));
    }

    /**
     * @param  value double
     * <p>trả về dữ liệu là định dạng tiền</p>
     * @return String <b>formatToMoney</b>
     */
    public static String formatToMoney(double value){
        if(value< 0)throw new IllegalArgumentException("Value must be positive");
        return new DecimalFormat("#,###.00").format(value);
    }
    private static HashMap<String, String> hm_tien = new HashMap<String, String>() {
        {
            put("0", "không");
            put("1", "một");
            put("2", "hai");
            put("3", "ba");
            put("4", "bốn");
            put("5", "năm");
            put("6", "sáu");
            put("7", "bảy");
            put("8", "tám");
            put("9", "chín");
        }
    };
    private static HashMap<String, String> hm_hanh = new HashMap<String, String>() {
        {
            put("1", "đồng");
            put("2", "mươi");
            put("3", "trăm");
            put("4", "nghìn");
            put("5", "mươi");
            put("6", "trăm");
            put("7", "triệu");
            put("8", "mươi");
            put("9", "trăm");
            put("10", "tỷ");
            put("11", "mươi");
            put("12", "trăm");
            put("13", "nghìn");
            put("14", "mươi");
            put("15", "trăm");

        }
    };

    /**
     * @param value String number
     * <p>convert Money to text</p>
     * @return return money to text with unicode(UTF-8)
     */
    public static String moneyToText(String value){
        /*
         *<p> hello</p>
         */
        String x= convertTotext(value);
        return new String(convertTotext(value).getBytes(StandardCharsets.UTF_8),Charset.forName("UTF-8"));
    }

    private static String convertTotext(String x) {
        String kq = "";
        x = x.replace(".", "");
        String arr_temp[] = x.split(",");
        String m = arr_temp[0];
        int count = m.length();
        String dau = "";
        int flag10 = 1;
        while (!m.equals("")) {
            if (m.length() <= 3 && m.length() > 1 && Long.parseLong(m) == 0) {

            } else {
                dau = m.substring(0, 1);
                if (count % 3 == 1 && m.startsWith("1") && flag10 == 0) {
                    kq += "mốt ";
                    flag10 = 0;
                } else if (count % 3 == 2 && m.startsWith("1")) {
                    kq += "mười ";
                    flag10 = 1;
                } else if (count % 3 == 2 && m.startsWith("0") && m.length() >= 2 && !m.substring(1, 2).equals("0")) {
                    //System.out.println("a  "+m.substring(1, 2));
                    kq += "lẻ ";
                    flag10 = 1;
                } else {
                    if (!m.startsWith("0")) {
                        kq += hm_tien.get(dau) + " ";
                        flag10 = 0;
                    }
                }
                if (count%3!=1 &&m.startsWith("0") && m.length()>1) {
                } else {
                    if (count % 3 == 2 && (m.startsWith("1") || m.startsWith("0"))) {//mười
                    } else {
                        kq += hm_hanh.get(count + "") + " ";
                    }
                }
            }
            m = m.substring(1);
            count = m.length();
        }
        kq=kq.substring(0, kq.length() - 1);
        return kq;
    }

    /**
     * @param number <p>Tham số muốn check</p>
     *               <p>Dùng để check xem đây có phải là số hay không</p>
     * @return <p>Nếu đây là số thì trả về true, không thì false</p>
     * <p><u>IllegalArgumentException</u> nếu số truyền vào rỗng</p>
     * <p><u>IllegalArgumentException</u> nếu số truyền vào không phải là số</p>
     */
    public static boolean isNumber(String number){
        number.trim();
        if(number.length()==0||number==null||number.isEmpty()||number.isBlank())
            throw new IllegalArgumentException("Number must not be empty");
        else if(number.matches("^[0-9]*$"))
            return true;
        else if(number.matches("^[0-9]*\\.[0-9]*$"))
            return true;
        else  throw new IllegalArgumentException("Number must be number");
    }
    public static double sum(double... values){
        double sum=0;
        for(double value:values){
            sum+=value;
        }
        return sum;
    }

    /**
     * @param values <p>Giá trị muốn cộng tổng</p>
     *               <p>Dùng để cộng rất nhiều giá trị lại với nhau, việc làm của bạn chỉ cần cho tham số dạng số và sẽ tính được tổng</p>
     * @return
     */
    public static double sum(String... values){
        for (String value:values){
            if(!isNumber(value))throw new IllegalArgumentException("Value must be number");
        }
        double sum=0;
        for(String value:values){
            sum+=Double.parseDouble(value);
        }
        return sum;
    }
    public static double sqrt(double value){
        return Math.sqrt(value);
    }
    public static String toString(double value){
        return String.valueOf(value);
    }
    public static double convertToDouble(String number){
        if(number.length()==0||number==null||number.isEmpty()||number.isBlank())
            throw new IllegalArgumentException("Number must not be empty");
        if(isNumber(number))
            return Double.parseDouble(number);
        else throw new IllegalArgumentException("Number must be number");
    }
    public static boolean compareInteger(double value1,double value2){
        if(value1==value2)return true;
        String value1_str=toString(value1).substring(0,toString(value1).indexOf(".")).trim();
        String value2_str=toString(value2).substring(0,toString(value1).indexOf(".")).trim();
        if(value1_str.equals(value2_str))return true;
        else return false;
    }
    public static double pow(double value,int power){
        return Math.pow(value,power);
    }
    public static double pow(String value,int power){
        return Math.pow(convertToDouble(value),power);
    }
    public static double sqrt(String value){
        return Math.sqrt(convertToDouble(value));
    }
    public static double getRandomNumber(double min,double max){
        return min+Math.random()*(max-min);
    }
    public static double getRandomNumber(String min,String max){
        return convertToDouble(min)+Math.random()*(convertToDouble(max)-convertToDouble(min));
    }
    public static double getRandomNumber(int min,int max){
        return min+Math.random()*(max-min);
    }



}

