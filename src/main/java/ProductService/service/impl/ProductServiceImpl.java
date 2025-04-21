package ProductService.service.impl;

import ProductService.entity.Product;
import ProductService.repository.ProductRepository;
import ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;
    public Product addProduct(Product product) {
        return repository.save(product);
    }
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }
    public List<Product> getProductsByCategory(String category) {
        return repository.findByCategory(category);
    }
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }
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
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
    @Override
    public void reduceStock(Long productId, int quantity) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setStock(product.getStock() - quantity);
        repository.save(product);
    }
    @Override
    public List<Product> searchByKeyword(String keyword) {
        return repository.searchByKeyword(keyword);
    }
}
