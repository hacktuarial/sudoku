import scala.util.control.Breaks._


class SudokuBoard(values: Array[Array[Char]]) {
  require(values forall (_.length == 9), "sudoku board must be 9x9")

  private val one_through_nine = Set('1', '2', '3', '4', '5', '6', '7', '8', '9')
  private val missing = '0'
  // indexes
  private val start = 0;
  private val end = 9;

  def partialVerify: Boolean = false

  def isCorrect: Boolean = {
    for {
      i <- start until end
    } if (!checkRow(i) || !checkColumn(i) || !checkSquare(i)) return false
    true
  }

  def isComplete: Boolean = {
    // check for NULLs
    for {
      i <- start until end
      j <- start until end
    } if (values(i)(j) == missing) return false
    true
  }

  def getValue(row: Integer, col: Integer): Char = values(row)(col)
  def getRow(index: Integer): Set[Char] = values(index).toSet
  def getCol(index: Integer): Set[Char] = {
    var columnValues: Set[Char] = Set()
    for {
      i <- start until end
    } columnValues += values(i)(index)
    return columnValues
  }

  def getIndex(row: Integer, col: Integer): Integer = {
    3 * (row / 3) + (col / 3)
  }

  def getSquare(row: Integer, col: Integer): Set[Char] = {
    getSquare(getIndex(row, col))
  }

  def getSquare(index: Integer): Set[Char] = {
    /* each square is 3x3
     square indexes are
     0  1  2
     3  4  5
     6  7  8
     */
    val firstRow = 3 * (index / 3) // integer division
    val firstCol = 3 * (index % 3)

    values(firstRow + 0).slice(firstCol, firstCol+3).toSet |
    values(firstRow + 1).slice(firstCol, firstCol+3).toSet |
    values(firstRow + 2).slice(firstCol, firstCol+3).toSet
  }

  def checkSquare(index: Integer): Boolean = {
    getSquare(index) == one_through_nine
  }

  def checkRow(index: Integer): Boolean = {
    getRow(index) == one_through_nine;
  }

  def checkColumn(index: Integer): Boolean = {
    // would it be easier to write a transpose method?
    getCol(index) == one_through_nine
  }

  def setValue(row: Integer, col: Integer, value: Char): SudokuBoard = {
    // modify a particular value of this board, return itself
    require(row >= start && row < end, "invalid row index")
    require(col >= start && col < end, "invalid column index")
    // handy to remove an element of a board for unit testing
    require((one_through_nine + missing) contains value, "invalid value")
    values(row)(col) = value
    this
  }

  def generateCandidates(): Set[SudokuBoard] = {
    // find the first missing value, generate possible boards
    var i, j = 0; // set scope to use outside of for loop
    for {
      i <- start until end
      j <- start until end
    } if (values(i)(j) == missing) {
      // find all possible values based on row, column, square constraints
      val alreadyUsed = getRow(i) | getCol(j) | getSquare(i, j) - missing
      val candidateValues = one_through_nine diff alreadyUsed
      return candidateValues.map(value => new SudokuBoard(this.values).setValue(i, j, value))
    } // else, keep looping
    Set()
  }

  override def toString: String = {
    val sep = "|"
    val divider = "-------------------"
     (divider + "\n" +
      "|" + values(0).mkString(sep) + "|\n" +
      "|" + values(1).mkString(sep) + "|\n" +
      "|" + values(2).mkString(sep) + "|\n" +
       divider + "\n" +
      "|" + values(3).mkString(sep) + "|\n" +
      "|" + values(4).mkString(sep) + "|\n" +
      "|" + values(5).mkString(sep) + "|\n" +
      divider + "\n" +
      "|" + values(6).mkString(sep) + "|\n" +
      "|" + values(7).mkString(sep) + "|\n" +
      "|" + values(8).mkString(sep) + "|\n" +
      divider
      )
  }

}
