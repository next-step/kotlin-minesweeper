package model

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import model.minemark.Mine
import model.minemark.Safety

@DisplayName("지뢰 설치")
class MineInstallationTest : StringSpec({

    "지뢰 개수, 마크, 포지션 선택 전략으로 생성" {
        MineInstallation(1, Mine, ZERO_POSITION_SELECTOR)
    }

    "지뢰 개수는 0보다 크거나 같아야 함" {
        listOf(Int.MIN_VALUE, -1)
            .forAll {
                shouldThrowExactly<IllegalArgumentException> {
                    MineInstallation(it, Mine, ZERO_POSITION_SELECTOR)
                }
            }
    }

    "원하는 마크로 지뢰 설치 가능" {
        // given
        val zeroPositionOneCountMineInstallation = MineInstallation(1, Mine, ZERO_POSITION_SELECTOR)
        // when
        val installedMineBoard: InstalledMineBoard =
            zeroPositionOneCountMineInstallation.installed(FOUR_ELEMENTS_CLEAN_MINE_BOARD)
        // then
        installedMineBoard shouldBe InstalledMineBoard(
            MineBoard(
                mapOf(
                    Position(0, 0) to Mine,
                    Position(0, 1) to Safety,
                    Position(1, 0) to Safety,
                    Position(1, 1) to Safety,
                )
            )
        )
    }

    "지뢰 설치 개수는 주어진 지뢰 보드 사이즈보다 작아야 함" {
        // given
        val maxCountMineInstallation = MineInstallation(Int.MAX_VALUE, Mine, ZERO_POSITION_SELECTOR)
        // when & then
        shouldThrowExactly<IllegalArgumentException> {
            maxCountMineInstallation.installed(FOUR_ELEMENTS_CLEAN_MINE_BOARD)
        }
    }

    "주어지는 포지션은 메인 보드에 존재해야 함" {
        // given
        val maxPositionInstallation =
            MineInstallation(1, Mine) { _, _ -> Position(Int.MAX_VALUE, Int.MAX_VALUE) }
        // when & then
        shouldThrowExactly<IllegalStateException> {
            maxPositionInstallation.installed(FOUR_ELEMENTS_CLEAN_MINE_BOARD)
        }
    }
})

val ZERO_POSITION_SELECTOR: (maxX: Int, maxY: Int) -> Position = { _, _ -> Position(0, 0) }
