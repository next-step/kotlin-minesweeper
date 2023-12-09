package map

data class MapInfo(val height: Int, val width: Int, val mineCnt: Int) {

    init {
        validateMapInfo(this)
    }

    private fun validateMapInfo(mapInfo: MapInfo) {
        val height = mapInfo.height
        val width = mapInfo.width
        val mineCnt = mapInfo.mineCnt
        require(height * width >= mineCnt) { ERR_MSG_MINE_OVERFLOW }
    }

    companion object {
        private const val ERR_MSG_MINE_OVERFLOW = "보드의 크기보다 지뢰가 더 많습니다."
    }
}
