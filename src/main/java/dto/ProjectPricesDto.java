package dto;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ProjectPricesDto {
    private String name;
    private int price;
}
