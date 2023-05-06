package dto;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class YoungestEldestWorkersDto {
    private String type;
    private String name;
    private String birthday;
}
