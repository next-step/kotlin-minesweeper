package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StreetsTest {
    private lateinit var streets: Streets

    @BeforeEach
    fun setUp() {
        streets = Streets(
            Street(0, 3),
            Street(1, 3),
            Street(2, 3)
        )
    }

    @Test
    fun `원하는 거리번호와 지점에 지뢰를 설치할 수 있다`() {
        // when
        streets.layMine(
            object : StreetChoosingStrategy {
                override fun getStreetNumber(streetCount: Int): Int = 0
            },
            object : PositionChoosingStrategy {
                override fun getPosition(streetLength: Int): Int = 2
            }
        )

        // then
        assertThat(streets[0].spots[2].toString()).isEqualTo(SpotSymbol.MINE.symbol)
    }

    @Test
    fun `전체 지뢰 개수`() {
        // given
        repeat(8) {
            streets.layMine(RandomStreetChoosingStrategy, RandomPositionChoosingStrategy)
        }

        // when
        val totalCount = streets.totalMineCount()

        // then
        assertThat(totalCount).isEqualTo(8)
    }
}
