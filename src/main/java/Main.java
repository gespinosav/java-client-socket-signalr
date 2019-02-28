import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

public class Main {

    public static void main(String[] args) {
        String url = "http://localhost:8088/sodexor";
        if (args.length == 1) {
            url = args[0];
        }

        HubConnection hubConnection = HubConnectionBuilder.create(url).build();
        hubConnection.start().blockingAwait();
        hubConnection.send("OnConnected", "Autorization Basic admin:pj2017");

    }
}
