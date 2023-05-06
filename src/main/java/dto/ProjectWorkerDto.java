package dto;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ProjectWorkerDto {
    private int projectId;
    private int workerId;
}
