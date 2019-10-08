package designpattern.mediator

import scala.collection.mutable.ListBuffer
import scala.collection.mutable._

trait IAlertMediator {
  def addSubscriberToDynamicAlert(alertobserver:   AlertObserver)
  def addSubscriberToThresholdAlert(alertobserver: AlertObserver)
  def publishAlert(alert:                          Alert)
}

class AlertMediator extends IAlertMediator {

  val thresoldAlertToObserver: Map[String, ListBuffer[AlertObserver]] =
    Map[String, scala.collection.mutable.ListBuffer[AlertObserver]]()

  val dynamicAlertToObserver: Map[String, ListBuffer[AlertObserver]] =
    Map[String, ListBuffer[AlertObserver]]()

  def addSubscriberToDynamicAlert(alertobserver: AlertObserver) = {
    var list: Option[ListBuffer[AlertObserver]] =
      dynamicAlertToObserver.get("dynamic")
    if (list == None) {
      list = Some(scala.collection.mutable.ListBuffer[AlertObserver]())
      dynamicAlertToObserver.put("dynamic", list.getOrElse(null))
    }
    list.getOrElse(null) += alertobserver
  }

  def addSubscriberToThresholdAlert(alertobserver: AlertObserver) = {
    var list: Option[ListBuffer[AlertObserver]] =
      thresoldAlertToObserver.get("threshold")
    if (list == None) {
      list = Some(ListBuffer[AlertObserver]())
      thresoldAlertToObserver.put("threshold", list.getOrElse(null))
    }
    list.getOrElse(null) += alertobserver
  }

  def publishAlert(alert: Alert) =
    if (alert.alertType == "dynamic") {
      for ((k, v) <- dynamicAlertToObserver) {
        v.foreach { e =>
          e.publish(alert);
        }
      }
    } else if (alert.alertType == "threshold") {
      for ((k, v) <- thresoldAlertToObserver) {
        v.foreach { e =>
          e.publish(alert);
        }
      }
    }
}
