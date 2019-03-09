
import org.scalatest.FunSuite



class SudokuBoardTest extends FunSuite {

  test("complete board") {
    // based on https://kjell.haxx.se/sudoku/
    val board = new SudokuBoard(Array(
      "473598126".toCharArray,
      "561432879".toCharArray,
      "892167354".toCharArray,
      "647385291".toCharArray,
      "935216748".toCharArray,
      "218749635".toCharArray,
      "154623987".toCharArray,
      "729851463".toCharArray,
      "386974512".toCharArray
    ))
    assert(board.checkComplete)
    assert(board.checkCorrect)
  }

  test("incomplete board") {
    val board = new SudokuBoard(Array(
      "473598126".toCharArray,
      "561432079".toCharArray,
      "892167354".toCharArray,
      "647385291".toCharArray,
      "930216748".toCharArray,
      "218749635".toCharArray,
      "154623987".toCharArray,
      "729851463".toCharArray,
      "386974510".toCharArray
    ))
    assert(!board.checkComplete)
  }

  test("print") {
    val board = new SudokuBoard(Array(
      "473598126".toCharArray,
      "561432879".toCharArray,
      "892167354".toCharArray,
      "647385291".toCharArray,
      "935216748".toCharArray,
      "218749635".toCharArray,
      "154623987".toCharArray,
      "729851463".toCharArray,
      "386974512".toCharArray
    ))
    println(board)
    assert(false)
  }

}
