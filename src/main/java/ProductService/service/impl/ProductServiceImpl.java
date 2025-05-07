package ProductService.service.impl;

import ProductService.entity.Product;
import ProductService.repository.ProductRepository;
import ProductService.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        return repository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            return repository.save(product);
        }).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackReduceStock")
    public void reduceStock(Long productId, int quantity) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setStock(product.getStock() - quantity);
        repository.save(product);
    }

    // Fallback method for reduceStock
    public void fallbackReduceStock(Long productId, int quantity, Throwable throwable) {
        System.out.println("Fallback triggered for reduceStock: " + throwable.getMessage());
        // You can add additional logic like notifying an admin or saving failed attempts
    }

    @Override
    public List<Product> searchByKeyword(String keyword) {
        return repository.searchByKeyword(keyword);
    }
}
