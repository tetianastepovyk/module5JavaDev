package dto;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class WorkerDto {
    private String name;
    private String birthday;
    private String level;
    private int salary;
}
