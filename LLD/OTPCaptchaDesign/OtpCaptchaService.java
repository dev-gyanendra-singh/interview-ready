package LLD.OTPCaptchaDesign;

import java.util.*;
import java.util.concurrent.*;

public class OtpCaptchaService {

    // Internal class to store OTP with timestamp
    static class OtpEntry {
        String otp;
        long timestamp;

        OtpEntry(String otp) {
            this.otp = otp;
            this.timestamp = System.currentTimeMillis();
        }

        boolean isExpired() {
            // Expire after 5 minutes (300_000 ms)
            return System.currentTimeMillis() - timestamp > 300_000;
        }
    }

    private final Map<String, OtpEntry> otpStore = new ConcurrentHashMap<>();
    private final Map<String, String> captchaStore = new ConcurrentHashMap<>();
    private final Random random = new Random();

    // Generate a 5-character alphanumeric captcha
    public String generateCaptcha(String sessionId) {
        String captcha = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        captchaStore.put(sessionId, captcha);
        return captcha;
    }

    // Verify captcha for session
    public boolean verifyCaptcha(String sessionId, String input) {
        String expected = captchaStore.get(sessionId);
        return expected != null && expected.equalsIgnoreCase(input);
    }

    // Generate 6-digit OTP for phone and store it
    public String sendOtp(String phone) {
        String otp = String.valueOf(random.nextInt(1_000_000));
        otpStore.put(phone, new OtpEntry(otp));
        System.out.println("Sending OTP to " + phone + ": " + otp); // Simulate SMS
        return otp; // For testing/demo only
    }

    // Verify OTP for a given phone
    public boolean verifyOtp(String phone, String inputOtp) {
        OtpEntry entry = otpStore.get(phone);
        if (entry == null || entry.isExpired()) {
            return false;
        }
        return entry.otp.equals(inputOtp);
    }

    // Main method to demo the flow
    public static void main(String[] args) throws InterruptedException {
        OtpCaptchaService service = new OtpCaptchaService();

        String sessionId = "session123"; // Simulated session ID

        // Step 1: Generate captcha
        String captcha = service.generateCaptcha(sessionId);
        System.out.println("CAPTCHA: " + captcha);

        // Step 2: User enters captcha (simulate correct input)
        if (service.verifyCaptcha(sessionId, captcha)) {
            System.out.println("Captcha verified ✅");

            // Step 3: Send OTP
            String phone = "1234567890";
            String sentOtp = service.sendOtp(phone);

            // Step 4: User enters OTP (simulate correct input)
            boolean otpValid = service.verifyOtp(phone, sentOtp);
            System.out.println("OTP verified: " + otpValid);

        } else {
            System.out.println("Captcha failed ❌");
        }
    }
}

