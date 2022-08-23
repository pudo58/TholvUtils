package org.tholv.ModelUtils;

public enum TableClassBootstrap{
    TABLE("table"),
    TABLE_BORDER(" table-bordered"),
    TABLE_SUCCESS(" table-success"),
    TABLE_PRIMARY(" table primary"),
    TABLE_SECONDARY(" table-secondary"),
    TABLE_DANGER(" table-danger"),
    TABLE_WARNING(" table-warning"),
    TABLE_INFO(" table-info"),
    TABLE_LIGHT(" table-light"),
    TABLE_DARK(" table-dark"),
    TABLE_STRIPED(" table-striped"),
    TABLE_HOVER(" table-hover"),
    ;
    public final String cssClass;
    TableClassBootstrap(String cssClass) {
        this.cssClass=cssClass;
    }
}
