
# DontCap4j

Dont-Cap API wrapper https://discordmodfurries.com




## Usage/Examples

Usage of DontCap4j is realy simple

```java
DontCapAPI api = DontCapAPI.builder()
                .username("foo")
                .password("bar")
                //.maxRetries(99) used for tryGetCaptchaUntilSuccess()
                .build();

Proxy proxy = new Proxy(ProxyType.HTTP, "host", 8080);
        
Options options = Options.builder()
        .sleep(1000)
        .build();

Captcha captcha = api.tryGetCaptchaUntilSuccess(
        "00000000-0000-0000-0000-000000000000", 
        "www.hcaptcha.com", 
        proxy, 
        options
);
        
System.out.println("Solved in " + captcha.getTimeString() + 
        " with " + captcha.getFailCount() + 
        " fails.  " + captcha.getKey()
);
```
Proxy support Authentification
```java
Proxy proxy = new Proxy(ProxyType.HTTP, "host", 8080, "username", "password");
```
You can also just don't put Options which can be found [Here](https://github.com/UwUDev/DontCap4j/blob/master/src/main/java/me/uwu/dont/cap/struct/Options.java)
