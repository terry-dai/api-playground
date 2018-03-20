package api.playground

import cats.effect.IO
import cats.implicits._
import org.http4s.dsl.io.{->, /, GET, Ok, Root, _}
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.{EntityEncoder, HttpService}



object Services {

  case class Tweet(id: Int, message: String)
  // defined class Tweet

  implicit def tweetEncoder: EntityEncoder[IO, Tweet] = ???
  // tweetEncoder: org.http4s.EntityEncoder[cats.effect.IO,Tweet]

  implicit def tweetsEncoder: EntityEncoder[IO, Seq[Tweet]] = ???
  // tweetsEncoder: org.http4s.EntityEncoder[cats.effect.IO,Seq[Tweet]]

  def getTweet(tweetId: Int): IO[Tweet] = ???
  // getTweet: (tweetId: Int)cats.effect.IO[Tweet]

  def getPopularTweets(): IO[Seq[Tweet]] = ???
  // getPopularTweets: ()cats.effect.IO[Seq[Tweet]]

  val tweetService = HttpService[IO] {
    case GET -> Root / "tweets" / "popular" =>
      Ok(getPopularTweets())
    case GET -> Root / "tweets" / IntVar(tweetId) =>
      getTweet(tweetId).flatMap(Ok(_))
  }


  val services = tweetService <+> helloWorldService

  val builder = BlazeBuilder[IO].bindHttp(8080, "localhost").mountService(helloWorldService, "/").mountService(services, "/api").start


  val helloWorldService = HttpService[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
  }
}
