package me.uwu.dont.cap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import me.uwu.dont.cap.exception.CaptchaException;
import me.uwu.dont.cap.exception.impl.CaptchaTimoutException;
import me.uwu.dont.cap.exception.impl.InternalServerErrorException;
import me.uwu.dont.cap.exception.impl.InvalidCredentialsException;
import me.uwu.dont.cap.struct.Capayload;
import me.uwu.dont.cap.struct.Captcha;
import me.uwu.dont.cap.struct.Options;
import me.uwu.dont.cap.struct.Proxy;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Builder
@RequiredArgsConstructor
public class DontCapAPI {
    static final Gson gson = new Gson();
    static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    static final MediaType mediaType = MediaType.parse("application/json");
    private final String username, password;
    @Builder.Default private final int maxRetries = 10;

    public Captcha getCaptcha(String siteKey, String host, Proxy proxy) throws CaptchaException {
        return getCaptcha(new Capayload(username, password, proxy.toString(), proxy.getType().getName(), siteKey, host));
    }

    public Captcha getCaptcha(String siteKey, String host, Proxy proxy, Options options) throws CaptchaException {
        Capayload capayload =new Capayload(username, password, proxy.toString(), proxy.getType().getName(), siteKey, host);
        capayload.setOptions(options);
        return getCaptcha(capayload);
    }

    public Captcha tryGetCaptchaUntilSuccess(String siteKey, String host, Proxy proxy, Options options) throws CaptchaException {
        Captcha captcha = null;
        int fails = 0;
        long start = System.currentTimeMillis();
        while (captcha == null) {
            if (fails >= maxRetries)
                throw new CaptchaException("Captcha failed too much times... " + fails + " fails.");

            try {
                captcha = getCaptcha(siteKey, host, proxy, options);
            } catch (Exception e) {
                fails++;
            }
        }
        long end = System.currentTimeMillis();
        captcha.setTime(end - start);
        captcha.setFailCount(fails);
        return captcha;
    }

    @SuppressWarnings("unused")
    public Captcha tryGetCaptchaUntilSuccess(String siteKey, String host, Proxy proxy) throws CaptchaException {
        Captcha captcha = null;
        int fails = 0;
        long start = System.currentTimeMillis();
        while (captcha == null) {
            if (fails >= maxRetries)
                throw new CaptchaException("Captcha failed too much times... " + fails + " fails.");

            try {
                captcha = getCaptcha(siteKey, host, proxy);
            } catch (Exception e) {
                fails++;
            }
        }
        long end = System.currentTimeMillis();
        captcha.setTime(end - start);
        captcha.setFailCount(fails);
        return captcha;
    }

    public Captcha getCaptcha(Capayload capayload) throws CaptchaException {
        String payload = capayload.toString();
        //System.out.println(payload);
        @SuppressWarnings("deprecation")
        RequestBody body = RequestBody.create(mediaType, payload);
        Request request = new Request.Builder()
                .url("http://185.250.251.189:10000/v1/solve")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "DontCap4j")
                .build();

        Response response = null;
        try {
            long start = System.currentTimeMillis();
            response = client.newCall(request).execute();
            @SuppressWarnings("ConstantConditions")
            JsonObject json = gson.fromJson(response.body().string(), JsonObject.class);
            if (json.get("error").getAsBoolean()) {
                //noinspection SpellCheckingInspection
                if (json.get("message").getAsString().equals("Wrong credidentials.")) // Dumbass can't write "credentials"
                    throw new InvalidCredentialsException(json.get("message").getAsString());
                throw new InternalServerErrorException(json.get("message").getAsString());
            } else {
                long end = System.currentTimeMillis();
                Captcha captcha = new Captcha(json.get("captcha_key").getAsString(), json.get("useragent").getAsString());
                captcha.setTime(end - start);
                return captcha;
            }
        } catch (IOException e) {
            //e.printStackTrace();
            if (e.getMessage().contains("time"))
                throw new CaptchaTimoutException("Captcha timed out");
            throw new CaptchaException(e.getMessage());
        } catch (Exception e) {
            throw new CaptchaException(e.getMessage());
        } finally {
            if (response != null)
                response.close();
        }
    }
}
