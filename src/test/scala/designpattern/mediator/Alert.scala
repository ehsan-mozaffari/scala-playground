package designpattern.mediator

trait Alert {
  def alertType:String
}

class DynamicAlert(var elementId:Int,var metricMetadataId:Int,var metricMetadataHourlyId:Int,var durationType:String,
                   var durationInterval:Int,var alertType:String) extends Alert {

}

class ThresholdAlert(var elementId:Int,var metricMetadataId:Int,var metricMetadataHourlyId:Int,var durationType:String,
                     var durationInterval:Int,var alertType:String) extends Alert {

}
