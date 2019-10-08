package designpattern.mediator

object Driver extends App {

  val alertMediator: IAlertMediator = new AlertMediator()
  val file:          AlertObserver  = new FileAlertSubscriber()
  val oracle:        AlertObserver  = new OracleAlertSubscriber()
  val haas:          AlertObserver  = new HaasAlertSubscriber()
  alertMediator.addSubscriberToDynamicAlert(file)
  alertMediator.addSubscriberToDynamicAlert(oracle)
  alertMediator.addSubscriberToThresholdAlert(haas)
  val dynamicAlert:   DynamicAlert   = new DynamicAlert(100, 200, 300, "PW", 5, "dynamic")
  val thresholdAlert: ThresholdAlert = new ThresholdAlert(100, 200, 300, "PW", 5, "threshold")
  alertMediator.publishAlert(dynamicAlert)
  alertMediator.publishAlert(thresholdAlert)

}
