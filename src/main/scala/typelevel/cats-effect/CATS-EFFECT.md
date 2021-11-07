# Cats effect docs
### Effects
The actions that do not evaluate to any meaningful value and yet the computer does something like show something on screen

### What is it?
Cats Effect should be considered as living in the same conceptual space as any other asynchronous runtime on the JVM (Netty, Akka, Tokio, RxJava, Vert.x)

Cats Effect defines features and functionality which simply doesn't exist in these ecosystems. As an example, neither Akka (via Future), Netty, nor Vert.x have any support for asynchronous cancelation (also known as "interruption"), meaning that basic functionality such as timeouts and concurrent error handling can result in resource leaks in a running application. Another example of functionality mismatch is the fiber-aware work-stealing runtime, which is present in Tokio and (to a lesser extent) Akka, but not in Netty or Vert.x. As a final example, asynchronous tracing is present to a limited degree in Vert.x and Akka, but absent from all other frameworks, and neither of these provide a version of this functionality which is performant enough for production use (unlike Cats Effect).

### Fibers
With fibers, there is no difference between a callback and a return because fibers are the fundamental abstraction in Cats Effect. And also, fibers are very lightweight and literally lightweight threads (often referred to as "green threads" or "coroutines").

The Cats Effect IO runtime implements fibers in roughly 150 bytes per fiber, meaning that you can literally create tens of millions of fibers within the same process without a problem, and your primary limiting factor will simply be memory.

As an example, any client/server application defined using Cats Effect will create a new fiber for each inbound request, much like how a naive server written using Java's ServerSocket will create a new Thread for each request (except it is both safe and fast to do this with fibers!). Because they are so lightweight, the act of creating and starting a new fiber is extremely fast in and of itself, making it possible to create very short-lived, "one-off" fibers whenever it is convenient to do so. Many of the functions within Cats Effect are implemented in terms of fibers under the surface, even ones which don't involve parallelism (such as memoize).

Each step in a fiber is an effect, and those effects are defined in sequence by explicitly composing them using the flatMap function. 

Every application has a "main fiber". This is very similar to the notion of a "main thread" in that it is the point at which control flow starts within the process. Conventionally in Cats Effect, this main fiber is defined using IOApp, and in particular by the effect returned by the run method:

```scala
object Hi extends IOApp.Simple {
  val run = IO.println("Hello") >> IO.println("World")
}
```

When one fiber `starts` another fiber, we generally say that the first fiber is the "parent" of the second one. This relationship is not directly hierarchical in that the parent can terminate before the child without causing any inconsistencies. However, certain properties of the fiber model make considerably more sense with this parent/child relationship in mind. For example, fibers may always observe and recover from errors (using something like `handleErrorWith` or `attempt`). This is conceptually similar to try/catch. Fibers may also observe their own cancelation (see below), but they cannot recover from it, meaning that they cannot continue executing after cancelation. Parent fibers may initiate cancelation in a child (via the `cancel` method), and can observe the final outcome of that child (which may be `Canceled`) and may continue executing after the child has terminated.

### Cancelation
By default, fibers are cancelable at all points during their execution. In practice, fiber cancelation most often happens in response to one of two situations: timeouts and concurrent errors.

```scala
import scala.concurrent.duration._

lazy val loop: IO[Unit] = IO.println("Hello, World!") >> loop

loop.timeout(5.seconds)   // => IO[Unit]
```
### Effect
An effect is a description of an action (or actions) that will be taken when evaluation happens. One very common sort of effect is IO

Such functions are often called "effectful": foo is an effectful function. because it runs side effect
```scala
def foo(str: String): IO[String] = ???
```

