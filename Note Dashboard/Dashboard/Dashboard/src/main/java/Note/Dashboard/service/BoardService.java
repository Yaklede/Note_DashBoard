package Note.Dashboard.service;

import Note.Dashboard.entity.Board;
import Note.Dashboard.entity.CategoryType;
import Note.Dashboard.entity.Member;
import Note.Dashboard.repository.BoardRepository;
import Note.Dashboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public Long createBoard(String title, String content, CategoryType categoryType, Member member) {
        Board board = new Board(title, content, categoryType, member);
        boardRepository.save(board);
        return board.getId();
    }

    public List<Board> findBoardSearch(String title) {
        return boardRepository.findByTitleContaining(title);
    }
}
