package quiz6;

import quiz5.CommunicationManager;

public class SpecialCommunicationManager implements CommunicationManager {
    private String commonServiceUrl;
    private String specialServiceUrl;

    public SpecialCommunicationManager(String commonServiceUrl, String specialServiceUrl) {
        this.commonServiceUrl = commonServiceUrl;
        this.specialServiceUrl = specialServiceUrl;
    }

    @Override
    public String sendRequest(String request) {
        String url = commonServiceUrl;
        if (request.contains("\"userMessage\":\"help\"") || request.contains("\"message\":\"help\"")) {
            url = specialServiceUrl;
        }
        /* For now, return a dummy response for testing,
        because I don't have an actual URL for this but the code is right :)
         */
        return "This is a response from the " + (url.equals(commonServiceUrl) ? "common" : "special") + " service.";
    }

    private String determineServiceUrl(String jsonRequest) {
        // Check if the JSON contains "help" (case-insensitive)
        if (jsonRequest.toLowerCase().contains("\"help\"")) {
            return specialServiceUrl;
        }
        return commonServiceUrl;
    }
}