package domain

import io.kotest.assertions.throwables.shouldThrow
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

    test("Board를 생성할때 height가 0보다 작으면 IllegalArgumentException이 발생한다.") {
        // given
        val boardCreator = BoardCreator(RandomMinePlacer())
        val height = -1
        val width = 3
        val mineCount = 2

        // when, then
        shouldThrow<IllegalArgumentException> {
            boardCreator.create(height, width, mineCount)
        }
    }

    test("Board를 생성할때 width가 0보다 작으면 IllegalArgumentException이 발생한다.") {
        // given
        val boardCreator = BoardCreator(RandomMinePlacer())
        val height = 3
        val width = -1
        val mineCount = 2

        // when, then
        shouldThrow<IllegalArgumentException> {
            boardCreator.create(height, width, mineCount)
        }
    }
})
