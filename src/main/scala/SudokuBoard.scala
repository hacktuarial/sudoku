import scala.util.hashing.MurmurHash3

class SudokuBoard(values: List[List[Char]]) {
  private val allowedSizes = Set(4, 9) // todo: add 25
  private val size = values.length
  private val sqrtSize: Int = math.sqrt(size).toInt

  require(values forall (allowedSizes contains _.length), "sudoku board must be 4x4 or 9x9")

  private val rightNumbers = ((1 to size) mkString).toList.toSet
  private val missing = '0'

  // methods to test for equality
  // see https://alvinalexander.com/scala/how-to-define-equals-hashcode-methods-in-scala-object-equality
  def canEqual(a: Any): Boolean = a.isInstanceOf[SudokuBoard]
  override def equals(that: Any): Boolean =
    that match {
      case that: SudokuBoard => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
  }
  override def hashCode(): Int = {
    val seed = 32079
    return MurmurHash3.listHash(values.flatten, seed)
  }

  def isCorrect: Boolean = {
    (0 until size).map(i => checkRow(i) && checkColumn(i) && checkSquare(i)).reduce(_ && _)
  }

  def isComplete: Boolean = {
    // check for NULLs
    !(0 until size).map(i => getRow(i) contains missing).reduce(_ || _)
  }

  def getValue(row: Integer, col: Integer): Char = values(row)(col)
  def getRow(index: Integer): Set[Char] = values(index).toSet
  def getCol(index: Integer): Set[Char] = values.map(_(index)).toSet
  def getIndex(row: Integer, col: Integer): Integer = sqrtSize * (row / sqrtSize) + (col / sqrtSize)
  def getSquare(row: Integer, col: Integer): Set[Char] = getSquare(getIndex(row, col))
  def getSquare(index: Integer): Set[Char] = {
    /* for a 9x9 board, each square is 3x3
     square indexes are
     0  1  2
     3  4  5
     6  7  8
     */
    val firstRow = sqrtSize * (index / sqrtSize) // integer division
    val firstCol = sqrtSize * (index % sqrtSize)

    (0 until sqrtSize)
      .map(i => values (firstRow + i) drop firstCol take sqrtSize toSet)
      .reduce((x, y) => x union y)
  }

  def checkSquare(index: Integer): Boolean = getSquare(index) == rightNumbers
  def checkRow(index: Integer): Boolean = getRow(index) == rightNumbers
  def checkColumn(index: Integer): Boolean = getCol(index) == rightNumbers

  def setValue(row: Integer, col: Integer, value: Char): SudokuBoard = {
    // return a new sudoku board with one value changed
    require(row >= 0 && row < size, "invalid row index")
    require(col >= 0 && col < size, "invalid column index")
    // handy to remove an element of a board for unit testing
    require((rightNumbers + missing) contains value, "invalid value")

    val newValues = values.updated(row, values(row).updated(col, value))
    new SudokuBoard(newValues)
  }

  def generateCandidates(): Set[SudokuBoard] = {
    // find the first missing value, generate possible boards
    for {
      i <- 0 until size
      j <- 0 until size
    } if (getValue(i, j) == missing) {
      // find all possible values based on row, column, square constraints
      val alreadyUsed = getRow(i) union getCol(j) union getSquare(i, j)
      val candidateValues = rightNumbers diff alreadyUsed
      return candidateValues.map(cv => this.setValue(i, j, cv))
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
