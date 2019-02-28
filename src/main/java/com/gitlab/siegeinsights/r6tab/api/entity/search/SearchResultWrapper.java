package com.gitlab.siegeinsights.r6tab.api.entity.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Holds the SearchResult's and counter
 */
public class SearchResultWrapper implements Serializable {

    @SerializedName("results")
    private List<SearchResult> results;

    @SerializedName("totalresults")
    private Integer totalResults;

    public List<SearchResult> getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * Validates if the actual number of results equals
     * to the number of specified results
     *
     * @return true, if totalResult count matches actual
     */
    public boolean isValidResultsCount() {
        return results.size() == totalResults;
    }
}
