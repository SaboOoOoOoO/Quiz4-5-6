package quiz5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteractionManager {
    private CommunicationManager communicationManager;
    private List<String> conversation;
    private Scanner scanner;

    public UserInteractionManager() {
        this.communicationManager = new DummyCommunicationManager();
        this.conversation = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void startChat() {
        System.out.println("Chat started. Type 'exit' to end the conversation.");
        while (true) {
            System.out.print("User: ");
            String userInput = scanner.nextLine();
            if ("exit".equalsIgnoreCase(userInput)) {
                break;
            }
            conversation.add("user: " + userInput);

            String jsonRequest = createJsonRequest(userInput);
            String response = communicationManager.sendRequest(jsonRequest);

            System.out.println("Chatbot: " + response);
            conversation.add("chatbot: " + response);
        }
        System.out.println("Chat ended.");
    }

    private String createJsonRequest(String userMessage) {
        StringBuilder jsonRequest = new StringBuilder();
        jsonRequest.append("{");
        jsonRequest.append("\"userMessage\":\"").append(userMessage).append("\",");
        jsonRequest.append("\"conversation\":[");

        for (int i = 0; i < conversation.size(); i++) {
            String entry = conversation.get(i);
            String[] parts = entry.split(": ", 2);
            if (parts.length == 2) {
                if (i > 0) {
                    jsonRequest.append(",");
                }
                jsonRequest.append("{");
                jsonRequest.append("\"role\":\"").append(parts[0]).append("\",");
                jsonRequest.append("\"message\":\"").append(parts[1]).append("\"");
                jsonRequest.append("}");
            }
        }

        jsonRequest.append("]");
        jsonRequest.append("}");
        return jsonRequest.toString();

    }

    public static void main(String[] args) {
        // Use DummyCommunicationManager for testing
        UserInteractionManager manager = new UserInteractionManager();
        manager.startChat();
    }
}