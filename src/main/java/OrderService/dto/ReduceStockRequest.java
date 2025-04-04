package OrderService.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReduceStockRequest {
    private int quantity;
}