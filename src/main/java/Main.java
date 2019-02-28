import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        String url = "http://localhost:8088/sodexor";
        String user = "usr";
        String password = "password";
        if (args.length == 1) {
            url = args[0];
            user = args[1];
            password = args[2];
        }

        String cred = user + ":" + password;

        String resp = "Basic " + Base64.getEncoder().encode( cred.getBytes() );
        String authParams = resp.replace("\n","");

        System.out.println("Establishing connection");
        HubConnection hubConnection = HubConnectionBuilder.create(url).build();
        System.out.println("Connected");
        hubConnection.start().blockingAwait();
        System.out.println("Authenticating");
        hubConnection.send("OnConnected", authParams);
        System.out.println("Success authentication");

    }
}
