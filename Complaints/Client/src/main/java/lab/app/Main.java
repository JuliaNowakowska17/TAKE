package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        String baseUrl = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";

        // Pobierz wszystkie skargi
        List<ComplaintDTO> allComplaints = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ComplaintDTO>>() {});

        System.out.println("Wszystkie skargi:");
        allComplaints.forEach(System.out::println);

        // Pobierz jedną otwartą skargę po ID
        ComplaintDTO openComplaintFromList = allComplaints.stream()
                .filter(c -> "open".equals(c.getStatus()))
                .findFirst()
                .orElse(null);

        if (openComplaintFromList == null) {
            System.out.println("Brak otwartych skarg.");
            client.close();
            return;
        }

        Long id = openComplaintFromList.getId();

        ComplaintDTO complaint = client.target(baseUrl + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(ComplaintDTO.class);

        System.out.println("\nPobrana otwarta skarga:");
        System.out.println(complaint);

        // Zmień status na closed
        complaint.setStatus("closed");

        client.target(baseUrl + "/" + id)
                .request()
                .put(Entity.entity(complaint, MediaType.APPLICATION_JSON));

        System.out.println("\nZmieniono status skargi id=" + id + " na closed.");

        // Pobierz wszystkie otwarte skargi
        List<ComplaintDTO> openComplaints = client.target(baseUrl)
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ComplaintDTO>>() {});

        System.out.println("\nOtwarte skargi po zmianie:");
        openComplaints.forEach(System.out::println);

        client.close();
    }
}