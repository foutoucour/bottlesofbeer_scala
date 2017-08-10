import java.io.File
import java.nio.file.Paths

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import scala.io.Source

import coveo.qatest.Lyrics

@RunWith(classOf[JUnitRunner])
class LyricsTests extends FunSuite with BeforeAndAfter {

  var lyrics: Lyrics = _
  val initialNumberOfBeers: Int = 14

  before {
    lyrics = new Lyrics(initialNumberOfBeers)
  }

  /**
    * When the number of bottles is 14
    * Then the number of bottles on the wall is 14 and "bottle" is plural
    * And 13 bottles remind and "bottle" is plural
    */
  test("whenBottleIsFourteen_thenWeDrink")  {
    val expected = "14 bottles of beer on the wall, 14 bottles of beer.\n" +
      "Take one down and pass it around, 13 bottles of beer on the wall.\n"
    assertResult(expected) {this.lyrics.buildLyricsFor(14)}
  }

  /**
    * When the number of bottles is 2
    * Then the number of bottles on the wall is 2 and "bottle" is plural
    * And 1 bottle stay and "bottle" is now singular
    */
  test("whenBottleIsTwo_thenWeDrinkButOnlyOneLeft") {
    val expected = "2 bottles of beer on the wall, 2 bottles of beer.\n" +
      "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    assertResult(expected) {this.lyrics.buildLyricsFor(2)}
  }

  /**
    * When the number of bottles is 1
    * Then the number of bottles on the wal is 1 and "bottle" is singular
    * And no more bottles on the wall.
    */
  test("whenBottleIsOne_thenWeDrinkButNoMore")  {
    val expected = "1 bottle of beer on the wall, 1 bottle of beer.\n" +
      "Take one down and pass it around, no more bottles of beer on the wall.\n"
    assertResult(expected) {this.lyrics.buildLyricsFor(1)}
  }

  /**
    * When the number of bottles is 0
    * Then no more bottles on the wall
    * And we buy the same number of bottles we started with
    */
  test("whenBottleIsZero_thenWeBuy")  {
    val expected = s"No more bottles of beer on the wall, no more bottles of beer.\n" +
      s"Go to the store and buy some more, ${this.initialNumberOfBeers} bottles of beer on the wall."
    assertResult(expected) {this.lyrics.buildLyricsFor(0)}
  }

  /**
    * When we drink 99 beers
    * Then we have the lyrics of the original song.
    *
    * The song is stored in song.txt file, at the root level of tests
    */
  test("All the song") {
    val path = Paths.get(System.getProperty("user.dir"), "..", "..","src", "test", "song.txt")
    val lines = Source.fromFile(path.toString).getLines.mkString("\n")
    val lyrics = new Lyrics(99)
    assertResult(lines) { lyrics.get }
  }
}
