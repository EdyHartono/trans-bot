package org.transbot.model;

import com.google.maps.model.AutocompletePrediction;

import java.util.List;

public class AddressSuggestion {
	private List<AutocompletePrediction> startSuggestionList;
	private List<AutocompletePrediction> endSuggestionList;

	public List<AutocompletePrediction> getStartSuggestionList() {
		return startSuggestionList;
	}

	public void setStartSuggestionList(List<AutocompletePrediction> startSuggestionList) {
		this.startSuggestionList = startSuggestionList;
	}

	public List<AutocompletePrediction> getEndSuggestionList() {
		return endSuggestionList;
	}

	public void setEndSuggestionList(List<AutocompletePrediction> endSuggestionList) {
		this.endSuggestionList = endSuggestionList;
	}
}
