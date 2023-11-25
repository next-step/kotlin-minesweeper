package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import minesweeper.domain.rule.RandomBoardGenerationRule
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `지뢰찾기 게임판은 높이,넓이,지뢰의개수,생성규칙을 받아 생성한다`() {
        shouldNotThrow<Exception> {
            Board(BoardMetadata(10, 10, 10), RandomBoardGenerationRule())
        }
    }
}
