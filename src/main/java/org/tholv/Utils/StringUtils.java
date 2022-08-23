package org.tholv.Utils;

import java.util.List;

public class StringUtils {
    public static StringUtils getInstance(){
        return new StringUtils();
    }
    public synchronized static String reverse(String str){
        return new StringBuilder(str).reverse().toString();
    }
    public synchronized static String concat(List<String> args){
        String concat="";
        for(String s : args){
            concat+=s;
        }
        return concat;
    }
    public synchronized static String concat(String str,String ...args){
        String concat="";
        concat+=str;
        for(String s : args){
            concat+=s;
        }
        return concat;
    }
    public synchronized static String concat(String str,List<String> args){
        String concat="";
        concat+=str;
        for(String s : args){
            concat+=s;
        }
        return concat;
    }
    public synchronized static String concat(List<String> args,String ...args2){
        String concat="";
        for(String s : args){
            concat+=s;
        }
        for(String s : args2){
            concat+=s;
        }
        return concat;
    }
    public synchronized static String concat(List<String> args,List<String> args2){
        String concat="";
        for(String s : args){
            concat+=s;
        }
        for(String s : args2){
            concat+=s;
        }
        return concat;
    }
    public synchronized static String concat(String str,List<String> args,String ...args2){
        String concat="";
        concat+=str;
        for(String s : args){
            concat+=s;
        }
        for(String s : args2){
            concat+=s;
        }
        return concat;
    }
    public synchronized static String concat(String str,List<String> args,List<String> args2){
        String concat="";
        concat+=str;
        for(String s : args){
            concat+=s;
        }
        for(String s : args2){
            concat+=s;
        }
        return concat;
    }
    public synchronized static String concat(String []params){
        String concat="";
        for(String s : params){
            concat+=s;
        }
        return concat;
    }
    public synchronized static String sortString(String str){
        char[] charArray = str.toCharArray();
        java.util.Arrays.sort(charArray);
        return new String(charArray);
    }
    public synchronized static String sortString(String str,String str2){
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        java.util.Arrays.sort(charArray);
        java.util.Arrays.sort(charArray2);
        return new String(charArray)+new String(charArray2);
    }
    public synchronized static String upperCaseFirstWord(String str){
        int i=0;
        str.trim();
     String[] strSplit = str.split(" ");
          String strUpperCaseFirstWord[]= new String[strSplit.length];
          for(String s : strSplit){
              strUpperCaseFirstWord[i]= String.valueOf(s.charAt(0)).toUpperCase()+s.substring(1,s.length())+" ";
              i++;
       }
        return concat(strUpperCaseFirstWord);
    }
    public synchronized static boolean isEmpty(String str){
        if(str==null)throw new IllegalArgumentException("String is not  null");
        str.trim();
        return str.isEmpty()||str.isBlank()||str.length()==0||str.equals("");
    }
}
