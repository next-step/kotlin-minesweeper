package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BoardCreatorTest : FunSpec({
    test("Board를 생성한다.") {
        // given
        val boardCreator = BoardCreator(RandomMinePlacer())
        val height = 3
        val width = 3
        val mineCount = 1

        // when
        val board = boardCreator.create(height, width, mineCount)

        // then
        board.cells.size shouldBe height * width
        board.mineCount shouldBe mineCount
    }
})
