package dto;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class LongestProjectDto {
    private String name;
    private int monthCount;
}
