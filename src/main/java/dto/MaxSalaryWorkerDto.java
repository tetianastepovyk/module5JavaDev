package dto;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class MaxSalaryWorkerDto {
    private String name;
    private int salary;
}