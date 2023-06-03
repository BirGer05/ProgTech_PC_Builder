import org.apache.log4j.Logger;
import MainPage.MainPage;
public class Main {
    private static Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        MainPage form = new MainPage();
        log.info("Main page shown");
    }
}