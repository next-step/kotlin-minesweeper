package domain

class Place(
    val number: Int,
    val placeType: PlaceType,
    val nearMineCount: Int
) {

    constructor(number: Int, placeType: PlaceType) : this(number = number, placeType = placeType, nearMineCount = 0)

    fun isMine(): Boolean {
        return placeType == PlaceType.MINE
    }

    fun isNotMine(): Boolean {
        return !isMine()
    }
}
