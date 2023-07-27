package view

import domain.Land
import domain.Mine

class OutputConsoleView {
    fun displayLand(land: Land) {
        println("지뢰찾기 게임 시작")
        for (y in 1..land.height) {
            for (x in 1..land.width) {
                if (land.mines.contains(Mine(x, y))) {
                    print("* ")
                } else {
                    print("C ")
                }
            }
            println()
        }
    }
}
