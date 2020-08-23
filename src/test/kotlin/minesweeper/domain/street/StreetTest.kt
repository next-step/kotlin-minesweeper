package minesweeper.domain.street

import minesweeper.domain.spot.SpotSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StreetTest {
    private lateinit var prevStreet: Street
    private lateinit var street: Street
    private lateinit var nextStreet: Street
    private lateinit var mineFullStreet: Street

    @BeforeEach
    fun setUp() {
        prevStreet = Street(0, 3)
        street = Street(1, 3)
        nextStreet = Street(2, 3)

        mineFullStreet = Street(0, 3)
    }

    @Test
    fun `원하는 위치에 지뢰를 설치할 수 있다`() {
        // when
        street.setMineSpot(object : PositionChoosingStrategy {
            override fun getPosition(streetWidth: Int): Int = 2
        })

        // then
        assertThat(street.spots[2].toString()).isEqualTo(SpotSymbol.MINE.symbol)
    }

    @Test
    fun `지뢰 개수`() {
        // given
        street.setMineSpot(RandomPositionChoosingStrategy)
        street.setMineSpot(RandomPositionChoosingStrategy)

        // when
        val count = street.mineCount()

        // then
        assertThat(count).isEqualTo(2)
    }

    @Test
    fun `지뢰가 가득차면 True, 아니면 False를 반환한다`() {
        // given
        repeat(3) {
            mineFullStreet.setMineSpot(RandomPositionChoosingStrategy)
        }

        // when
        val isFull = mineFullStreet.isFullOfMines()
        val isAlsoFull = street.isFullOfMines()

        // then
        assertThat(isFull).isTrue()
        assertThat(isAlsoFull).isFalse()
    }

    @Test
    fun `좌우 지뢰 개수의 합을 구한다`() {
        // given
        street.setMineSpot(object : PositionChoosingStrategy {
            override fun getPosition(streetWidth: Int): Int = 0
        })
        street.setMineSpot(object : PositionChoosingStrategy {
            override fun getPosition(streetWidth: Int): Int = 2
        })

        // when
        street.addMineCountAround(street.spots)

        val mineCount = street.spots[1].getMineCount()

        // then
        assertThat(mineCount).isEqualTo(2)
    }

    @Test
    fun `위 아래 다른 거리의 지뢰 개수를 더한다`() {
        // given
        prevStreet.setMineSpot(object : PositionChoosingStrategy {
            override fun getPosition(streetWidth: Int): Int = 0
        })
        nextStreet.setMineSpot(object : PositionChoosingStrategy {
            override fun getPosition(streetWidth: Int): Int = 0
        })

        // when
        street.addMineCountAround(prevStreet.spots)
        street.addMineCountAround(nextStreet.spots)

        val mineCount = street.spots[1].getMineCount()

        // then
        assertThat(mineCount).isEqualTo(2)
    }
}
