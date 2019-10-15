## Mediator pattern

the mediator pattern defines an object that encapsulates how a set of objects interact.  
With the mediator pattern, communication between objects is encapsulated within a mediator object. Objects no longer communicate directly with each other, but instead communicate through the mediator. This reduces the dependencies between communicating objects, thereby reducing coupling  

Chain of Responsibility, Command, Mediator, and Observer, address how you can decouple senders and receivers, but with different trade-offs. Chain of Responsibility passes a sender request along a chain of potential receivers. Command normally specifies a sender-receiver connection with a subclass. Mediator has senders and receivers reference each other indirectly. Observer defines a very decoupled interface that allows for multiple receivers to be configured at run-time.

  
Mediator and Observer are competing patterns. The difference between them is that Observer distributes communication by introducing "observer" and "subject" objects, whereas a Mediator object encapsulates the communication between other objects. We've found it easier to make reusable Observers and Subjects than to make reusable Mediators.

On the other hand, Mediator can leverage Observer for dynamically registering colleagues and communicating with them.

Mediator is similar to Facade in that it abstracts functionality of existing classes. acade defines a simpler interface to a subsystem, it doesn't add new functionality, and it is not known by the subsystem classes 

https://sourcemaking.com/design_patterns/mediator
https://refactoring.guru/design-patterns/mediator
https://examples.javacodegeeks.com/core-java/java-mediator-design-pattern-example/
https://dzone.com/articles/design-patterns-mediator