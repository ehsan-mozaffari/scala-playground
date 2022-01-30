# scala-playground
The sandbox of Scala 

See the [Todo](./doc/TODO.md) list

## Run tests
### Running all tests
```scala
sbt test
```
### Running tests independently
```shell
sbt 'testOnly algorithm.trees.BTreeSpec'
# Or simply enter 
sbt
# And in sbt console run the following
> ~testOnly algorithm.trees.BTreeSpec
```
Note: `~` behind the command is for repeating on changing file

### Scalafmt
Turn formatter off in Intellij Idea(version 13+):

Go to `File > Settings > Editor > Code Style > Formatter tab > Enable formatter markers in comments`
and in your code use the following:
```scala
// @formatter:off
//...
// @formatter:on 
```


