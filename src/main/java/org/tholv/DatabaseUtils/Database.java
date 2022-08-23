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

    public static void main(String[] args) {
        List<String> columnNameOutPut = new ArrayList<>();
        columnNameOutPut.add("id");
        columnNameOutPut.add("name");
        columnNameOutPut.add("age");
        columnNameOutPut.add("address");
        List<Object[]> resultDataQuery = new ArrayList<>();
        Object[] row = new Object[4];
        row[0] = "1";
        row[1] = "John";
        row[2] = "25";
        row[3] = "London";
        resultDataQuery.add(row);
        row = new Object[4];
        row[0] = "2";
        row[1] = "Jane";
        row[2] = "30";
        row[3] = "Paris";
        resultDataQuery.add(row);
        row = new Object[4];
        row[0] = "3";
        row[1] = "Mary";
        row[2] = "28";
        row[3] = "New York";
        resultDataQuery.add(row);
        row = new Object[4];
        row[0] = "4";
        row[1] = "Peter";
        row[2] = "35";
        row[3] = "Berlin";
        resultDataQuery.add(row);
        row = new Object[4];
        row[0] = "5";
        row[1] = "Sally";
        row[2] = "32";
        row[3] = "London";
        resultDataQuery.add(row);
        row = new Object[4];
        row[0] = "6";
        row[1] = "Paul";
        row[2] = "35";
        row[3] = "Paris";
        resultDataQuery.add(row);
        row = new Object[4];
        row[0] = "7";
        row[1] = "Mark";
        row[2] = "35";
        row[3] = "Tho";
        resultDataQuery.add(row);
        TableClassBootstrap[] model={TableClassBootstrap.TABLE,TableClassBootstrap.TABLE_STRIPED,TableClassBootstrap.TABLE_HOVER};
        System.out.println(Database.getInstance().queryRenderTable(resultDataQuery, columnNameOutPut, "tableName",model));

    }

}
