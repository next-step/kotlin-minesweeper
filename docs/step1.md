# 1단계 - 지뢰 찾기(그리기)

## 기능 요구사항

> 지뢰 찾기를 변형한 프로그램을 구현한다.

* [x] 높이와 너비, 지뢰 개수를 입력받을 수 있다.
    * [x] 높이를 입력하세요.
        * [x] 높이가 0인 값이 들어오면 예외를 발생한다.
    * [x] 너비를 입력하세요.
        * [x] 너비가 0인 값이 들어오면 예외를 발생한다.
    * [x] 지뢰는 몇 개인가요?
        * [x] 지뢰의 갯수가 0이 들어오면 예외를 발생한다.
* [] 지뢰는 가급적 랜덤에 가깝게 배치한다.
    * [] 전략패턴을 이용해서 지뢰 보드를 만든다.
    * [] Blck -> List<Block> -> List<Row> 형태로 만든다.

## 클래스 설계

* [x] 높이(height: Int)
    * [x] 0 이상의 값만 가능
* [x] 너비(width: Int)
    * [x] 0 이상의 값만 가능
* [x] 지뢰 수(minesCount: Int)
    * [x] 0이상의 값만 가능
* [x] 블록
    * Position(x: Int, y:Int) -> 추후 X/Y 값으로 Board에서 특정 셀을 찾기 위함
    * isMines():Boolean 를 가지고 있어야한다.
* [x] 위치
    * [x] x/y는 0 이상의 값이어야 한다

## 다음에 적용할 때 사용될 코드(처음에 하드코딩했던 코드)

```kotlin
// for ((x, mutableList) in board.withIndex()) {
//     for ((y, element) in mutableList.withIndex()) {
//         setNumber(x, y, width.width, height.height, board)
//     }
// }
//
// println(
//     board.joinToString(separator = "\n") {
//         it.fold(StringBuilder()) { acc: StringBuilder, i: Int -> acc.append("$i\t") }
//     }
// )
//
// outputView.startGame()
// println(
//     board.joinToString(separator = "\n") {
//         it.fold(StringBuilder()) { acc: StringBuilder, i: Int ->
//             acc.append(
//                 if (i == -1) "*" else "C"
//             )
//         }
//     }
// )
// 랜덤 값으로 지뢰 주입
//
// fun setNumber(row: Int, column: Int, width: Int, height: Int, board: MutableList<MutableList<Int>>) {
//     if (board[row][column] == 99) {
//         board[row][column] = getMineNumber(row, column, width, height, board)
//     }
// }
//
// private fun getMineNumber(
//     row: Int,
//     column: Int,
//     width: Int,
//     height: Int,
//     board: MutableList<MutableList<Int>>
// ): Int {
//     var mineCnt = 0
//     if (isExistMine(row - 1, column - 1, width, height, board)) mineCnt++
//     if (isExistMine(row - 1, column, width, height, board)) mineCnt++
//     if (isExistMine(row - 1, column + 1, width, height, board)) mineCnt++
//     if (isExistMine(row, column - 1, width, height, board)) mineCnt++
//     if (isExistMine(row, column + 1, width, height, board)) mineCnt++
//     if (isExistMine(row + 1, column - 1, width, height, board)) mineCnt++
//     if (isExistMine(row + 1, column, width, height, board)) mineCnt++
//     if (isExistMine(row + 1, column + 1, width, height, board)) mineCnt++
//     return mineCnt
// }
//
// private fun isExistMine(
//     row: Int,
//     column: Int,
//     width: Int,
//     height: Int,
//     board: MutableList<MutableList<Int>>
// ): Boolean {
//     if (row < 0 || row >= width || column < 0 || column >= height) {
//         return false
//     }
//
//     return board[row][column] == -1
// }
```
