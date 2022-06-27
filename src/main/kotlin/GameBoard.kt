class GameBoard(private val gameSettingInfo: GameSettingInfo) {


    init {
        val board = mutableListOf<List<List<Square>>>()
    }
}


class GameSettingInfo(val height: Int, val width: Int, val mineCount: Int)
