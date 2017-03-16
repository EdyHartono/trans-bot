package org.transbot.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.*;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transbot.model.AddressSuggestion;
import org.transbot.model.State;
import org.transbot.repository.StateRepository;
import org.transbot.service.APIUtilsService;
import org.transbot.service.AddressSugggestionService;
import org.transbot.service.MessageConverterService;

import java.util.List;

@Service
public class MessageConverterServiceImpl implements MessageConverterService {

    @Autowired
    private APIUtilsService apiUtilsService;

    @Autowired
    private AddressSugggestionService addressSugggestionService;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertMessageToRoute(String userId, String message) {
        State state = stateRepository.findByUserId(userId);
        String value="";
        GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyCo3aUKGX_8Sus5P_ACSdzqqUhvYqyE4Sg");
        String startPoint = message.substring(0, message.indexOf("-"));
        String destination = message.substring(message.indexOf("-") + 1);

        if (state == null) {
            try {
                AddressSuggestion addressSuggestion = addressSugggestionService.getSuggestionRoute(userId, startPoint, destination);
                value+="Start Point Suggestion\n";
                int index=1;
                for(AutocompletePrediction autocompletePrediction:addressSuggestion.getStartSuggestionList())
                {
                    value+=index+". "+autocompletePrediction.description+"\n";
                    index+=1;
                }
                value+="\nEnd Point Suggestion\n";
                index=1;
                for(AutocompletePrediction autocompletePrediction:addressSuggestion.getEndSuggestionList())
                {
                    value+=index+". "+autocompletePrediction.description+"\n";
                    index+=1;
                }
                return  value;
            } catch (Exception e) {
                //TODO: error log
            }

        } else {
           try
           {
               if(!Character.isDigit(startPoint.charAt(0))&&!Character.isDigit(destination.charAt(0)))
               {
                   value+="Format input salah";
               }
               else
               {
                   //TODO cari ke dalam databasenya
                   //jangan lupa ambil yang descriptionnya aja ya
                   //cari ke dalam repository
                   String startPointFinal;
                   String destinationFinal;

                   //buat dapatin geo locationnya
                   //disini kita kirim si pilihan user aja
                   GeocodingResult[] geocodingStartPointResult= GeocodingApi.geocode(
                           geoApiContext,
                          startPoint
                   ).await();

                   GeocodingResult[] geocodingEndPointResult= GeocodingApi.geocode(geoApiContext,destination).await();
                   DirectionsResult directionsResult= DirectionsApi.getDirections(
                           geoApiContext,
                           geocodingStartPointResult[0].formattedAddress,
                           geocodingEndPointResult[0].formattedAddress
                   ).mode(TravelMode.TRANSIT).transitMode(TransitMode.BUS).language("ID").await();
                   ///

                   int step=1;
                   DirectionsStep[] directionsSteps = directionsResult.routes[0].legs[0].steps;

                   for (DirectionsStep directionsStep : directionsSteps){
                       value += String.format("%d.%s\n",step++, directionsStep.htmlInstructions);
                       if(directionsStep.travelMode == TravelMode.TRANSIT){

                           value +=new StringBuilder().appendCodePoint(0x0001F68B).toString()+directionsStep.transitDetails.departureStop.name+ "\n";
                           value += new StringBuilder().appendCodePoint(0x0001F68F).toString()+directionsStep.transitDetails.arrivalStop.name + "\n";
                       }
                   }
               }
           }catch(Exception e)
           {
               //TODO error log
               value+="Format input salah";
           }
//			return autocompletePredictions;
            return null;
        }
    return value;
        //results[0].geometry.location.lat(), results[0].geometry.location.lng()

    }
}
