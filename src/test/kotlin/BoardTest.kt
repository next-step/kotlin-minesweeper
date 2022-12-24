import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec
import minesweeper.domain.Board

class BoardTest : FreeSpec({
    "보드를 생성하기 위해서" - {

        val height = 10
        val width = 10
        val mineCount = 10

        "높이, 너비, 지뢰 개수 인자가 필요하다" {
            shouldNotThrowAny { Board(height = height, width = width, mineCount = mineCount) }
        }
    }
})
