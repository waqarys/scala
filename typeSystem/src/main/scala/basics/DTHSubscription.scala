package basics

import java.time.LocalDateTime

case class Channel(name: String)
case class TVPackage(name: String, channels: Set[Channel])

sealed trait Plan
case object Monthly extends Plan
case object BiAnnually extends Plan
case object Annual extends Plan

case class SubscriptionPeriod(startDate: LocalDateTime, endDate: LocalDateTime)

case class Subscription(
                       name: String,
                       defaultTVPackages: Map[TVPackage, Plan],
                       additionalTVPackages: Map[TVPackage, Plan],
                       additionalChannels: Map[Channel, Plan],
                       subscriptionPeriod: SubscriptionPeriod
                       )

object DTHSubscription extends App {

  val channelOne = Channel("Öne")
  val channelTwo = Channel("Two")
  val channelSportsOne = Channel("SportsOne")
  val channelSportsTwo = Channel("SportsTwo")

  val tvPackageGen = TVPackage("GenPack", Set(channelOne, channelTwo, channelSportsTwo))
  val tvPackageSports = TVPackage("SportsPack", Set(channelSportsOne, channelSportsTwo))

  val goldSunscription = Subscription(
    "Gold",
    Map(tvPackageGen -> Annual),
    Map(tvPackageSports -> Monthly),
    Map.empty[Channel, Plan],
    SubscriptionPeriod(
      LocalDateTime.of(2019, 5, 9, 12, 0),
      LocalDateTime.of(2020, 5, 9, 12, 0)
    )
  )

  println(goldSunscription.subscriptionPeriod)
}
