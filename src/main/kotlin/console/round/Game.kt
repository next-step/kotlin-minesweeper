package console.round

import map.Height
import map.Map
import map.Width
import map.move.Position

class Game(
    val map: Map,
    var round: Round,
) {
    fun start(
        choosePosition: (Height, Width) -> Position?,
        afterRound: (Map) -> Unit,
    ): GameResult {
        while (true) {
            val result =
                round
                    .play(choosePosition = choosePosition)
                    ?.also { afterRound(it) }
                    ?: return GameResult.Lose

            round = Round(map = result)
        }
    }

    companion object {
        fun init(map: Map): Game = Game(map = map, round = Round(map = map))
    }
}
