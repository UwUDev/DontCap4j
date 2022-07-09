import me.uwu.dont.cap.DontCapAPI;
import me.uwu.dont.cap.exception.CaptchaException;
import me.uwu.dont.cap.struct.Captcha;
import me.uwu.dont.cap.struct.Options;
import me.uwu.dont.cap.struct.Proxy;
import me.uwu.dont.cap.struct.ProxyType;

@SuppressWarnings({"GrazieInspection", "SpellCheckingInspection"}) // Bruh
public class Tester {
    public static void main(String[] args) throws CaptchaException {
        DontCapAPI api = DontCapAPI.builder()
                .username("foo")
                .password("bar")
                //.maxRetries(99)
                .build();


        Proxy proxy = new Proxy(ProxyType.HTTP, "host", 8080);
        // Or you can do   Proxy proxy = new Proxy(ProxyType.HTTP, "host", 8080, "username", "password");

        Options options = Options.builder()
                .sleep(1000) // milliseconds
                .build();

        // Can be done without Options   Captcha captcha = api.getCaptcha("00000000-0000-0000-0000-000000000000", "www.hcaptcha.com", proxy);
        Captcha captcha = api.tryGetCaptchaUntilSuccess("00000000-0000-0000-0000-000000000000", "www.hcaptcha.com", proxy, options);
        System.out.println("Solved in " + captcha.getTimeString() + " with " + captcha.getFailCount() + " fails.  " + captcha.getKey());
    }
}
