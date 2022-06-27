package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlaceTest {

    @Test
    fun `지뢰가 있는 자리인지에 대한 메서드 테스트`() {
        val place = Place(number = 0, placeType = PlaceType.MINE)

        assertThat(place.isMine()).isTrue
    }

    @Test
    fun `지뢰가 없는 자리인지에 대한 메서드 테스트`() {
        val place = Place(number = 0, placeType = PlaceType.NOT_MINE)

        assertThat(place.isNotMine()).isTrue
    }
}
