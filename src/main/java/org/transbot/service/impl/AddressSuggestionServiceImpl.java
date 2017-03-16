package org.transbot.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transbot.model.AddressSuggestion;
import org.transbot.model.State;
import org.transbot.repository.StateRepository;
import org.transbot.service.AddressSugggestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edy Hartono on 3/16/2017.
 */
@Service
public class AddressSuggestionServiceImpl  implements AddressSugggestionService{

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public AddressSuggestion getSuggestionRoute(String userId,String startPoint, String destination) {
        LatLng jakartaLatLng = new LatLng(-6.21462, 106.84513);
        AddressSuggestion addressSuggestion  = new AddressSuggestion();
        State state = new State();
        try{
            GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyCo3aUKGX_8Sus5P_ACSdzqqUhvYqyE4Sg");

            AutocompletePrediction[] startPointPredictions = PlacesApi.placeAutocomplete(
                    geoApiContext,
                    startPoint
            ).location(jakartaLatLng).await();

            AutocompletePrediction[] endPointPredictions=PlacesApi.placeAutocomplete(
                    geoApiContext,
                    destination
            ).location(jakartaLatLng).await();

            List<AutocompletePrediction> autocompleteStartPredictions = new ArrayList<AutocompletePrediction>();
            autocompleteStartPredictions.add(startPointPredictions[0]);
            autocompleteStartPredictions.add(startPointPredictions[1]);
            autocompleteStartPredictions.add(startPointPredictions[2]);

            List<AutocompletePrediction> autocompleteEndPredictions = new ArrayList<AutocompletePrediction>();
            autocompleteEndPredictions.add(endPointPredictions[0]);
            autocompleteEndPredictions.add(endPointPredictions[1]);
            autocompleteEndPredictions.add(endPointPredictions[2]);


            addressSuggestion.setStartSuggestionList(autocompleteStartPredictions);
            addressSuggestion.setEndSuggestionList(autocompleteEndPredictions);
            String address = objectMapper.writeValueAsString(addressSuggestion);


            state.setAddress(address);
            state.setState((short)1);
            state.setUserId(userId);
            stateRepository.save(state);

        } catch(Exception e){
            //TODO: error log
        }
        return addressSuggestion;
    }
}
