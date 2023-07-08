package pi.dev.realestate.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int id;
    private String content;
    private String username;
    private int userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
