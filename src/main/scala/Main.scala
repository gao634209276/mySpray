import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Main extends App {
  implicit val system = ActorSystem("simple-service")
  val service = system.actorOf(Props[SampleServiceActor], "simple-service")

  IO(Http) ! Http.Bind(service, "127.0.0.1", port = 8090)
}