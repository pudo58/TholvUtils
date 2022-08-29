package org.tholv.DatabaseUtils;

import org.tholv.ModelUtils.TableClassBootstrap;

import javax.xml.crypto.Data;
import java.lang.reflect.GenericArrayType;
import java.util.*;

public class Database<T> {
    T value;
    List<T> list;


    public static Database getInstance(){
        return new Database();
    }
    public String queryRenderTable(List<Object[]> resultDataQuery, List<String> columnNameOutPut,String tableId){
        String result="";
        result+="<table id='"+tableId+"' class='table table-striped table-hover'>";
        result+="<thead>\n";
        result+="<tr>\n";
        for(String s : columnNameOutPut){
            result+="<th>"+s+"</th>\n";
        }
        result+="</tr>\n";
        result+="</thead>\n";
        result+="<tbody>\n";
        for(Object[] row : resultDataQuery){
            result+="<tr>\n";
            for(Object s : row) {
                result+="<td>"+s+"</td>\n";
            }
            result+="</tr>\n";
        }
        result+="</tbody>\n";
        result+="</table>\n";
        return result;
    }
    public T get(){
        return this.value;
    }
    public void setValue(T value){
        this.value=value;
    }
    public List<T> setAll(List<T> value){
        return this.list=value;
    }
    public List getAll(){
        return list;
    }
    public List<Object[]>convertToListObject(List <T> value){
        this.setAll(value);
        return getAll();
    }
    public String queryRenderTable(List<Object[]> resultDataQuery, List<String> columnNameOutPut, String tableName, TableClassBootstrap[] cssClass){
        String Class="";
        for (int i=0;i<cssClass.length;i++)Class+=cssClass[i].cssClass;
        String result="";
        result+="<table id='"+tableName+"' class='"+Class+"'>";
        result+="<thead>\n";
        result+="<tr>\n";
        for(String s : columnNameOutPut){
            result+="<th>"+s+"</th>\n";
        }
        result+="</tr>\n";
        result+="</thead>\n";
        result+="<tbody>\n";
        for(Object[] row : resultDataQuery){
            result+="<tr>\n";
            for(Object s : row) {
                result+="<td>"+s+"</td>\n";
            }
            result+="</tr>\n";
        }
        result+="</tbody>\n";
        result+="</table>\n";
        return result;
    }
    public String queryRenderTable(List<Object[]> resultDataQuery, List<String> columnNameOutPut, String tableName, TableClassBootstrap[] cssClass,String css){
        String Class="";
        for (int i=0;i<cssClass.length;i++)Class+=cssClass[i].cssClass;
        String result="";
        result+="<table id='"+tableName+"' class='"+Class+"' style='"+css+"'>";
        result+="<thead>\n";
        result+="<tr>\n";
        for(String s : columnNameOutPut){
            result+="<th>"+s+"</th>\n";
        }
        result+="</tr>\n";
        result+="</thead>\n";
        result+="<tbody>\n";
        for(Object[] row : resultDataQuery){
            result+="<tr>\n";
            for(Object s : row) {
                result+="<td>"+s+"</td>\n";
            }
            result+="</tr>\n";
        }
        result+="</tbody>\n";
        result+="</table>\n";
        return result;
    }

    public static void main(String[] args) {
        class demo {
            int id,age;

            public demo(int id, int age) {
                this.id = id;
                this.age = age;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }
        List<demo> list=new ArrayList<>();
        list.add(new demo(1,2));
        list.add(new demo(1,2));
        list.add(new demo(1,2));
        list.add(new demo(1,2));
        Database<demo>db=Database.getInstance();
        for(Object[] x:db.convertToListObject(list)){
            System.out.println(x);
        }
    }


}
