package coveo.qatest

/**
  * Represents the song of X Bottles Of Beer. The song can be 99 bottles long but the size can change.
  */
/**
  * Constructor
  *
  * @param numberOfBottles number of bottle to drink
  */
class Lyrics(var numberOfBottles: Int) {
  /**
    * Return the lyrics of the song according to the number of bottles provided in the constructor.
    *
    * @return the lyrics of the song
    */
  def get: String = {
    (for (i <- this.numberOfBottles to 0 by -1) yield buildLyricsFor(i)).mkString("")
  }

  /**
    * Build the sentence for the lyrics according to the number of bottles provided.
    *
    * @param numberBottles the number of bottles
    * @return the lyrics related to the number of bottles
    */
  def buildLyricsFor(numberBottles: Int): String = numberBottles match {
    case 0 =>
      s"No more bottles of beer on the wall, no more bottles of beer.\n" + s"Go to the store and buy some more, ${makeBottleAgreeWith(this.numberOfBottles)} of beer on the wall."
    case 1 =>
      "1 bottle of beer on the wall, 1 bottle of beer.\n" + "Take one down and pass it around, no more bottles of beer on the wall.\n"
    case _ =>
      val string1 = makeBottleAgreeWith(numberBottles)
      val string2 = makeBottleAgreeWith(numberBottles - 1)
      s"$string1 of beer on the wall, $string1 of beer.\n" + s"Take one down and pass it around, $string2 of beer on the wall.\n"
  }

  /**
    * Simple function to determite if the word "bottle" should be plural or singular
    *
    * @param numberBottles the number of bottles to consider to make the word "bottle" agree with.
    * @return the number of bottle and the word bottle
    */
  private def makeBottleAgreeWith(numberBottles: Int) = numberBottles match {
    case 1 =>
      "1 bottle"
    case _ =>
      s"$numberBottles bottles"
  }
}
