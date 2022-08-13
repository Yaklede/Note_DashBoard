package Note.Dashboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public Board(String title, String content, CategoryType categoryType) {
        this.title = title;
        this.content = content;
        this.categoryType = categoryType;
    }

    public Board(String title, String content, CategoryType categoryType, Member member) {
        this.title = title;
        this.content = content;
        this.categoryType = categoryType;
        this.member = member;
    }
}
