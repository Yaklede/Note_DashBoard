package Note.Dashboard.dto;

import Note.Dashboard.entity.CategoryType;
import lombok.Data;
import lombok.Getter;

@Getter
public class BoardDto {
    private String title;
    private String content;

    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
