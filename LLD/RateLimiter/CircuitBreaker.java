package LLD.RateLimiter;

public class CircuitBreaker {

    public class ExternalApiService {

   //     @CircuitBreaker(name = "myService", fallbackMethod = "fallback")
        public String callExternalApi() {
            // Simulate failure
            if (Math.random() < 0.7) {
                throw new RuntimeException("API failed");
            }
            return "Success!";
        }

        public String fallback(Throwable t) {
            return "Fallback response due to error: " + t.getMessage();
        }
    }

   // @RestController
    public class ApiController {

        private final ExternalApiService service;

        public ApiController(ExternalApiService service) {
            this.service = service;
        }

       // @GetMapping("/test-circuit-breaker")
        public String test() {
            return service.callExternalApi();
        }
    }
}
