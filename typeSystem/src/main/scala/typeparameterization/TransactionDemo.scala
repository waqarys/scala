package typeparameterization

import java.time.LocalDateTime
import java.util.UUID

sealed abstract class TransactionType

trait Online extends TransactionType
trait Offline extends TransactionType

case object Card extends Online
case object Wallet extends Online
case object NetBanking extends Online

case object Cash extends Offline
case object Cheque extends Offline

case class Amount(value: Double, currency: String)

/*case class Transaction(
                      id: UUID,
                      amount: Amount,
                      txnTime: LocalDateTime,
                      txnType: TransactionType
                      )*/

case class Transaction[+T](
                        id: UUID,
                        amount: Amount,
                        txnTime: LocalDateTime,
                        txnType: T
                      )

object TransactionUtils {
  def compensate(transaction: Transaction[Online]): Boolean = {
    println(s"Transaction is being compensated")
    true

    /*transaction.txnType match {
      case Card | Wallet | NetBanking => -println(s"Transaction is being compensated"); true
      case Cash | Cheque => println(s"Transaction can not compensated"); false
    }*/
  }
}

object TransactionDemo extends App {

  import TransactionUtils._

  val transaction = Transaction(UUID.randomUUID(), Amount(2, "USD"), LocalDateTime.now(), Card)
  val offlineTxn = Transaction(UUID.randomUUID(), Amount(2, "USD"), LocalDateTime.now(), Cash)

  println(s"${transaction.txnType} Transaction: ${transaction}")
  println(s"${offlineTxn.txnType} Transaction: ${offlineTxn}")

  val compensatoryTxn = compensate(transaction)
  //val compensatoryOfflineTxn = compensate(offlineTxn)

  println(compensatoryTxn)
  //println(compensatoryOfflineTxn)
}
