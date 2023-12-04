package minesweeper

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.inspectors.forAll
import io.kotest.inspectors.forAllKeys
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.MineSweeperCreator
import minesweeper.domain.MineSweeperSize
import minesweeper.domain.Position
import minesweeper.domain.cell.MineCell
import minesweeper.domain.cell.SafeCell

data class MineSweeperCreatorTestData(
    val mineSweeperSize: MineSweeperSize,
    val minePosition: List<Position>
)

class MineSweeperCreatorTest : FunSpec({
    context("MineSweeperCreater로 지도찾기 생성") {
        withData(
            listOf(
                MineSweeperCreatorTestData(
                    MineSweeperSize(2, 2),
                    listOf(Position(0, 0), Position(0, 1))
                ),
                MineSweeperCreatorTestData(
                    MineSweeperSize(5, 5),
                    listOf(Position(0, 0), Position(0, 1))
                )
            )
        ) { (mineSweeperSize, minePosition) ->
            val mineSweeperMap = MineSweeperCreator(
                mineSweeperSize = mineSweeperSize,
                minePosition = minePosition
            ).createMineSweeperMap()

            mineSweeperMap.forAllKeys { (x, y) ->
                x shouldBeInRange 0..mineSweeperSize.width
                y shouldBeInRange 0..mineSweeperSize.height
            }

            mineSweeperMap.forAll { (position, cell) ->
                if (minePosition.contains(position)) {
                    cell.shouldBeTypeOf<MineCell>()
                } else {
                    cell.shouldBeTypeOf<SafeCell>()
                }
            }
        }
    }
})
