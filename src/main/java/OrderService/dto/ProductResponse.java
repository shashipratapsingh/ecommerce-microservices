package OrderService.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;
}
