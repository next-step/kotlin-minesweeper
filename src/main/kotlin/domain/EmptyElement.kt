package domain

class EmptyElement private constructor(override val displayCharacter: String, override val location: Location) : MapElement {
    var mineCnt = 0

    // TODO : 주변에 지뢰가 몇 개 있는지 계산하여 mineCnt에 업데이트하는 함수 추가

    companion object {
        private const val EMPTY_ELEMENT_CHAR = "C"

        fun create(x: Int, y: Int): EmptyElement {
            return EmptyElement(EMPTY_ELEMENT_CHAR, Location(x, y))
        }
    }
}
