package domain

import domain.strategy.MinePositionsSelectStrategy
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class BoardFactoryTest {
    @Test
    fun `가로 5, 세로 5 크기의 지뢰판은 총 25개의 블록을 가진다`() {
        val mineCount = MineCount(5)
        val board = BoardFactory().generate(rectangle(5, 5), mineCount)
        board.getBlocks().size shouldBe 25
        board.getBlocks().count { it.value.isMine() } shouldBe mineCount.value
    }

    @RepeatedTest(10)
    fun `보드 생성시 각 블록은 주변 지뢰 수를 계산하여 가지고 생성된다`() {
        val mineCount = MineCount(1)
        val board = BoardFactory().generate(rectangle(2, 2), mineCount)
        val block = board.getBlockByPosition(Position.of(1, 1))
        require(block != null)
        if (block.isMine()) {
            block.getMineCount() shouldBe 0
        } else {
            block.getMineCount() shouldBe 1
        }
    }

    /**
     * 1 X 1
     * 1 1 1
     * 0 0 0
     */
    @Test
    fun `1,1 위치에 지뢰를 가진 3x3 사이즈의 보드 생성`() {
        val mineCount = MineCount(1)
        val board = BoardFactory().generate(rectangle(3, 3), mineCount, Test1To1MinePositionSelectStrategy())
        board.getBlocks().map {
            if (it.value is Mine) {
                it.key shouldBe Position.of(1, 1)
            } else {
                it.key shouldNotBe Position.of(1, 1)
            }
        }
    }
}

class Test1To1MinePositionSelectStrategy : MinePositionsSelectStrategy {
    override fun getMinePositions(positions: List<Position>, mineCount: MineCount): List<Position> {
        return listOf(Position.of(1, 1))
    }
}
