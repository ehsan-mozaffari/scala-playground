package designpattern.mediator

trait AlertObserver {
  def publish(alert: Alert): Unit
}

class FileAlertSubscriber() extends AlertObserver {
  def publish(alert: Alert): Unit = {
    println("FileAlertSubscriber called")
    // logges the breach into a file for further analysys
  }
}

class OracleAlertSubscriber() extends AlertObserver {
  def publish(alert: Alert): Unit = {
    // insert alert into oracle for further analyses
    println("OracleAlertSubscriber called")
  }
}

class HaasAlertSubscriber() extends AlertObserver {
  def publish(alert: Alert): Unit = {
    println("HaasAlertSubscriber called")
    // logges the breach into a file for further analysys
  }
}
