package next.step.minesweeper.domain.mine.generator

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.mine.MineCount
import org.junit.jupiter.api.assertThrows

class RandomMineGeneratorTest : DescribeSpec({

    describe("RandomMineGenerator") {
        context("지뢰 생성을 요청하면") {
            it("입력한 개수만큼 지뢰 제공") {
                RandomMineGenerator.generate(
                    Board.covered(10, 10),
                    MineCount(10)
                ) shouldHaveSize 10
            }
        }
        context("지뢰를 board보다 더 많이 생성하려고 하면") {
            it("예외 발생") {
                assertThrows<IllegalArgumentException> {
                    RandomMineGenerator.generate(
                        Board.covered(10, 10),
                        MineCount(101)
                    )
                }
            }
        }
    }
})
