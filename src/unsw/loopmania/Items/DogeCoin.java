package unsw.loopmania.Items;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class DogeCoin extends Item {

    public DogeCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);

        // final String apiKey = "88b2ab51-14d6-48df-87d9-5a2a9ded99a1";
        //TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice/BTC.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = "";
            while (null != (str = br.readLine())) {
                System.out.println(str);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
