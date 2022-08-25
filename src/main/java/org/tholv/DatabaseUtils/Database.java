package org.tholv.DatabaseUtils;

import org.tholv.ModelUtils.TableClassBootstrap;
import java.util.ArrayList;
import java.util.List;

public class Database {
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
}
