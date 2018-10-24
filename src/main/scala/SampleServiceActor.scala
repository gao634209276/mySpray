import akka.actor.Actor
import spray.http.MediaTypes
import spray.routing.HttpService

import scala.concurrent.Future

class SampleServiceActor extends Actor with SampleRoute {
  def actorRefFactory = context

  def receive = runRoute(
    //testRoute ~ stuffRoute ~ segmentRoute ~ pathRoute ~ queryRoute ~ headRoute ~ reactiveRoute
    testRoute
  )
}


trait SampleRoute extends HttpService {

  // 用  spray json 来做序列化
  import spray.httpx.SprayJsonSupport._
  // 自定义序列化
  import MyProtocol._

  val testRoute = {
    path("test") {
      get {
        path("a") {
          complete("That's my test path: /test/a!")
        }
      } ~
        get {
          complete("That's my test path: /test!")
        } ~
        post {
          complete("test posted!")
        }
    }
  }

  val stuffRoute = {
    path("stuff") {
      // 返回json数据
      respondWithMediaType(MediaTypes.`application/json`) {
        get {
          complete(Stuff(1, "my stuff"))
        } ~ post {
          entity(as[Stuff]) { stuff =>
            complete(Stuff(stuff.id + 100, stuff.data + " posted"))
          }
        }
      }
    }
    //~
    //  get {
    //    complete("I exist!")
    //  }
  }

  //多层路由：spray 提供了  pathPrefix 和 pathEnd 指令
  val segmentRoute = pathPrefix("segment") {
    get {
      pathPrefix(Segment) { a => {
        pathPrefix(Segment) { b => {
          pathEnd {
            complete(s"path is: /jobs/$a/$b")
          }
        }
        }
      }
      }
    }
  }

  val pathRoute = pathPrefix("junk") {
    pathPrefix("mine") {
      pathEnd {
        get {
          complete("MINE!")
        }
      }
    } ~ pathPrefix("yours") {
      pathEnd {
        get {
          complete("YOURS!")
        }
      }
    }
  }

  val queryRoute = path("params") {
    get {
      parameters('req, 'opt.?) { (req, opt) =>
        complete(s"Req: $req, Opt: $opt")
      }
    }
  }

  val headRoute = path("headers") {
    get {
      headerValueByName("ct-remote-user") { userId =>
        complete(userId)
      }
    }
  }


  val reactiveRoute = path("reactive") {
    get {
      //complete(Future {"I'm reactive!"})
      complete("I'm reactive!")
    }
  }

}