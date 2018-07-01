package parcel

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://www.allgaeu-parcel-service.com")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-GB,en;q=0.5")
		.doNotTrackHeader("1")
		.userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_7 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Access-Control-Request-Headers" -> "content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "http://www.allgaeu-parcel-service.com")

	val headers_8 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json;charset=utf-8",
		"Origin" -> "http://www.allgaeu-parcel-service.com")

    val uri2 = "http://asset.allgaeu-parcel-service.com:8444/ui"
    val uri3 = ":8443/parcel/size"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_7")
			.options(uri3 + "")
			.headers(headers_7)
			.resources(http("request_8")
			.post(uri3 + "")
			.headers(headers_8)
			.body(RawFileBody("RecordedSimulation_0008_request.txt"))))

	setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}
