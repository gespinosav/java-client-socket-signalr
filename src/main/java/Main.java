import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.functions.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        String url = "http://localhost:8088/sodexor";
        String user = "usr";
        String password = "password";
        if (args.length == 1) {
            url = args[0];
            user = args[1];
            password = args[2];
        }

        String cred = user + ":" + password;

        String resp = "Basic " + Base64.getEncoder().encode(cred.getBytes());
        String authParams = resp.replace("\n", "");

        System.out.println("Establishing connection");
        HubConnection hubConnection = HubConnectionBuilder.create(url)
                .withHeader("Authorization",authParams).build();
        System.out.println("Connected");
        hubConnection.start().blockingAwait();

    }
}
