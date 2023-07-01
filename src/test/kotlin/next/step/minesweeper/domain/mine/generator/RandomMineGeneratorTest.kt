package next.step.minesweeper.domain.mine.generator

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.board.BoardHeight
import next.step.minesweeper.domain.board.BoardWidth
import next.step.minesweeper.domain.mine.MineCount

class RandomMineGeneratorTest : DescribeSpec({

    describe("RandomMineGenerator") {
        context("지뢰 생성을 요청하면") {
            it("입력한 개수만큼 지뢰 제공") {
                RandomMineGenerator.generate(
                    Board.of(BoardHeight(10), BoardWidth.of(10)),
                    MineCount.of(10)
                ) shouldHaveSize 10
            }
        }
    }
})
