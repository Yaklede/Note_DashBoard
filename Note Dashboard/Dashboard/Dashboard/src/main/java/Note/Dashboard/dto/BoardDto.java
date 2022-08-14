package Note.Dashboard.dto;

import Note.Dashboard.entity.CategoryType;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDto {
    private String title;
    private String content;
    private CategoryType categoryType;
    private LocalDateTime createdTime;

    public BoardDto(String title, String content, CategoryType categoryType, LocalDateTime createdTime) {
        this.title = title;
        this.content = content;
        this.categoryType = categoryType;
        this.createdTime = createdTime;
    }
}
