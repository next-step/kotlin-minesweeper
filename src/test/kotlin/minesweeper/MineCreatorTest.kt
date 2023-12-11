package minesweeper

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldBeUnique
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import minesweeper.domain.MineCreator
import minesweeper.domain.MineSweeperSize

data class CreateMineTestData(
    val mineSweeperSize: MineSweeperSize,
    val countOfMine: Int
)

class MineCreatorTest : FunSpec({
    context("너비, 높이와 지뢰의 개수로 지뢰위치 생성") {
        withData(
            listOf(
                CreateMineTestData(
                    MineSweeperSize(2, 2), 2
                ),
                CreateMineTestData(
                    MineSweeperSize(2, 2), 2
                ),
            )
        ) { (mineSweeperSize, countOfMine) ->
            val mineList = MineCreator.create(
                mineSweeperSize,
                countOfMine
            )

            mineList.forAll {
                it.x shouldBeInRange (0 until mineSweeperSize.width)
                it.y shouldBeInRange (0 until mineSweeperSize.height)
            }
            mineList.shouldBeUnique()
            mineList.count() shouldBe countOfMine
        }

        context("지뢰찾기 지도의 너비보다 지뢰가 많으면 에러 발생") {
            withData(
                listOf(
                    CreateMineTestData(
                        MineSweeperSize(2, 2), 10
                    ),
                    CreateMineTestData(
                        MineSweeperSize(2, 3), 10
                    ),
                )
            ) { (mineSweeperSize, countOfMine) ->
                shouldThrowWithMessage<IllegalArgumentException>(
                    "Mines are too many!"
                ) {
                    MineCreator.create(
                        mineSweeperSize,
                        countOfMine
                    )
                }
            }
        }
    }
})
