package quiz5;

public class DummyCommunicationManager implements CommunicationManager {
    @Override
    public String sendRequest(String jsonRequest) {

        System.out.println("Sending request: " + jsonRequest);
        return "This is a dummy response from the chatbot.";
    }
}
