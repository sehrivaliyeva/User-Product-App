package az.codebridge.task.dto;

import az.codebridge.task.status.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductRequestDto {
    private String name;

    private Double price;

    private Integer stockQuantity;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private LocalDateTime createdAt;
}
