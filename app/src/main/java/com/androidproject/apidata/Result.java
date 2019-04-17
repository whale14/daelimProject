
package com.androidproject.apidata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private double relevance;
    private String id;
    private String title;
    private String description;
    private String category;
    private List<String> labels = null;
    private int rank;
    private int localRank;
    private int duration;
    private String start;
    private String end;
    private String updated;
    private String firstSeen;
    private String timezone;
    private List<Double> location = null;
    private String scope;
    private String country;
    private List<List<String>> placeHierarchies = null;
    private String state;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public double getRelevance() {
        return relevance;
    }

    public void setRelevance(double relevance) {
        this.relevance = relevance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getLocalRank() {
        return localRank;
    }

    public void setLocalRank(int localRank) {
        this.localRank = localRank;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(String firstSeen) {
        this.firstSeen = firstSeen;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<List<String>> getPlaceHierarchies() {
        return placeHierarchies;
    }

    public void setPlaceHierarchies(List<List<String>> placeHierarchies) {
        this.placeHierarchies = placeHierarchies;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Result{" +
                "relevance=" + relevance +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", labels=" + labels +
                ", rank=" + rank +
                ", localRank=" + localRank +
                ", duration=" + duration +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", updated='" + updated + '\'' +
                ", firstSeen='" + firstSeen + '\'' +
                ", timezone='" + timezone + '\'' +
                ", location=" + location +
                ", scope='" + scope + '\'' +
                ", country='" + country + '\'' +
                ", placeHierarchies=" + placeHierarchies +
                ", state='" + state + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
