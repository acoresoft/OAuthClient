import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class t {
	 public static void main(String[] args) throws Exception {
		  String a[] = new String[9];
		  String key="1";
	      for(int i=0;i<a.length;i++ ) {

	          a[i] = String.valueOf((int)(9*(Math.random())));
	          key+=a[i];
	          

	      }
	      System.out.print(key);

	 }

}
