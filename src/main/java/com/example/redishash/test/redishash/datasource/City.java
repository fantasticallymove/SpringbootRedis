package com.example.redishash.test.redishash.datasource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 伺服器城市資訊實例
 */
@Entity
@Table(name = "city", schema = "ry")
public class City implements Serializable {
    private String cityName;
    private String cityI18N;
    private String oceanLocationField;
    private String oceanLocationFieldI18N;
    private String rowCol;
    private String isCrossIsland;
    private String neighborhoodCities;
    private Integer crossDistance;
    private Integer visitCount;

    @Id
    @Basic
    @Column(name = "city_name", table = "city")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "city_i18n", table = "city")
    public String getCityI18N() {
        return cityI18N;
    }

    public void setCityI18N(String cityI18N) {
        this.cityI18N = cityI18N;
    }

    @Basic
    @Column(name = "ocean_location_field", table = "city")
    public String getOceanLocationField() {
        return oceanLocationField;
    }

    public void setOceanLocationField(String oceanLocationField) {
        this.oceanLocationField = oceanLocationField;
    }

    @Basic
    @Column(name = "ocean_location_field_i18n", table = "city")
    public String getOceanLocationFieldI18N() {
        return oceanLocationFieldI18N;
    }

    public void setOceanLocationFieldI18N(String oceanLocationFieldI18N) {
        this.oceanLocationFieldI18N = oceanLocationFieldI18N;
    }

    @Basic
    @Column(name = "row_col", table = "city")
    public String getRowCol() {
        return rowCol;
    }

    public void setRowCol(String rowCol) {
        this.rowCol = rowCol;
    }

    @Basic
    @Column(name = "is_cross_island", table = "city")
    public String getIsCrossIsland() {
        return isCrossIsland;
    }

    public void setIsCrossIsland(String isCrossIsland) {
        this.isCrossIsland = isCrossIsland;
    }

    @Basic
    @Column(name = "neighborhood_cities", table = "city")
    public String getNeighborhoodCities() {
        return neighborhoodCities;
    }

    public void setNeighborhoodCities(String neighborhoodCities) {
        this.neighborhoodCities = neighborhoodCities;
    }

    @Basic
    @Column(name = "cross_distance", table = "city")
    public Integer getCrossDistance() {
        return crossDistance;
    }

    public void setCrossDistance(Integer crossDistance) {
        this.crossDistance = crossDistance;
    }

    @Basic
    @Column(name = "visit_count", table = "city")
    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }
}
