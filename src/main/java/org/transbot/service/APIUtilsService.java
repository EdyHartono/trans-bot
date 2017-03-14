package org.transbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Erwin on 14-Mar-17.
 */
@Service
public class APIUtilsService {

    @Value("${google.api.key}")
    private String GoogleAPIKey;

    public String getGoogleAPIKey() {
        return GoogleAPIKey;
    }
}
