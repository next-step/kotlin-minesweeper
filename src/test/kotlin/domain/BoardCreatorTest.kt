package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BoardCreatorTest : FunSpec({
    test("Board를 생성할때 지뢰의 개수를 지정한다.") {
        // given
        val boardCreator = BoardCreator(RandomMinePlacer())
        val height = 3
        val width = 3
        val mineCount = 2

        // when
        val board = boardCreator.create(height, width, mineCount)

        // then
        board.cells().allCells().filter { it.hasMine }.size shouldBe mineCount
    }
})
