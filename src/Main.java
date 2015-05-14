import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.http.HttpParameters;

public class Main {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

    	OAuthConsumer consumer = new DefaultOAuthConsumer("122122212", "9a76b47946555f5b4a575d5f5f01");
        OAuthProvider provider = new DefaultOAuthProvider("http://127.0.0.1:8080/school/oauth/xrequest_token",
                "http://127.0.0.1:8080/school/oauth/xaccess_token",
                "http://127.0.0.1:8080/school/oauth/xauthorize");
        System.out.println("Fetching request token...");
        String authUrl = provider.retrieveRequestToken(consumer,OAuth.OUT_OF_BAND);
        System.out.println("Request token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());

        System.out.println("Now visit:\n" + authUrl
                + "\n... and grant this app authorization");
        System.out.println("Enter the verification code and hit ENTER when you're done:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String verificationCode = br.readLine();
        System.out.println("verificationCode="+verificationCode);
        System.out.println("Fetching access token...");
        provider.setOAuth10a(true);
        provider.retrieveAccessToken(consumer,verificationCode.trim());
        System.out.println("Access token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());
        
        URL url = new URL("http://127.0.0.1:8080/school/phone/ad/ad_list?newsid=1&menuid=00010001&loginname=a&nickname=aa&password=123456");
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("POST");

        consumer.sign(request);

        System.out.println("Sending request...");
        request.connect();

        System.out.println("Response: " + request.getResponseCode() + " "
                + request.getResponseMessage());
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(request.getInputStream()));
		for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2.readLine())
		{
			sb.append(s);
		}
        System.out.println(sb.toString());

    }
}
