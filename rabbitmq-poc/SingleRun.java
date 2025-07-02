import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SingleRun {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String auth = Base64.getEncoder()
                .encodeToString("user:password".getBytes(StandardCharsets.UTF_8));
        long end = System.currentTimeMillis() + 12_000;
        int count = 0;
        while (System.currentTimeMillis() < end) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/messages"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic " + auth)
                    .POST(HttpRequest.BodyPublishers.ofString("{\"content\":\"hello\"}"))
                    .build();
            long start = System.currentTimeMillis();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            long duration = System.currentTimeMillis() - start;
            System.out.printf("Request %d -> %d (%d ms)%n", ++count, response.statusCode(), duration);
            Thread.sleep(1000);
        }
        System.out.println("Total requests: " + count);
    }
}
