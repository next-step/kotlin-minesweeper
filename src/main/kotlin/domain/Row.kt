package domain

data class Row(private var _places: List<Place>) {

    val places: List<Place>
        get() = _places

    fun transformPlaceIndexed(block: (Int, Place) -> Place) {
        _places = _places.mapIndexed(block)
    }
}
