package dto;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class MaxProjectsClientDto {
    private String name;
    private int projectCount;
}
