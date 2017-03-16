package org.transbot.service;

import com.google.maps.model.AutocompletePrediction;
import org.transbot.model.AddressSuggestion;

import java.util.List;

/**
 * Created by Edy Hartono on 3/16/2017.
 */
public interface AddressSugggestionService {

    AddressSuggestion getSuggestionRoute(String userId,String startPoint, String destination);
}
