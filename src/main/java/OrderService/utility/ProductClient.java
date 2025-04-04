package OrderService.utility;

import OrderService.dto.ProductResponse;
import OrderService.dto.ReduceStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "http://localhost:8083")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    @PostMapping("/products/{id}/reduce-stock")
    void reduceStock(@PathVariable Long id, @RequestBody ReduceStockRequest request);
}
