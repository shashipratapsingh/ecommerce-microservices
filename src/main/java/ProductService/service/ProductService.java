package ProductService.service;
import ProductService.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
      Product addProduct(Product product);
      List<Product> getAllProducts();
      Optional<Product> getProductById(Long id);
      List<Product> getProductsByCategory(String category);
      List<Product> getProductsByPriceRange(double minPrice, double maxPrice);
      Product updateProduct(Long id, Product updatedProduct);
      void deleteProduct(Long id);
      void reduceStock(Long productId, int quantity);
      List<Product> searchByKeyword(String keyword);
}
