package dto;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ProjectDto {
    private int clientID;
    private String startDate;
    private String finishDate;
}
