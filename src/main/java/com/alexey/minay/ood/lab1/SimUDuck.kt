package com.alexey.minay.ood.lab1

fun main(){
    val mallardDuck = MallardDuck()
    playWithDuck(mallardDuck)

    val redHeadDuck = RedHeadDuck()
    playWithDuck(redHeadDuck)

    val rubberDuck = RubberDuck()
    playWithDuck(rubberDuck)

    val decoyDuck = DecoyDuck()
    playWithDuck(decoyDuck)

    val modelDuck = ModelDuck()
    playWithDuck(modelDuck)
    modelDuck.setFlyBehavior(FlyWithWings()::fly)
    playWithDuck(modelDuck)
}

fun drawDuck(duck: Duck){
    duck.display()
}

fun playWithDuck(duck: Duck){
    drawDuck(duck)
    duck.quack()
    duck.fly()
    duck.fly()
    duck.fly()
    duck.dance()
    duck.swim()
}

abstract class Duck(
        private val quackBehavior: () -> Unit,
        private var flyBehavior: () -> Unit,
        private val danceBehavior:() -> Unit
) {
    fun quack(){
        quackBehavior()
    }

    fun fly(){
        flyBehavior()
    }

    fun dance(){
        danceBehavior()
    }

    fun swim() {
        println("I'm swimming")
    }

    fun setFlyBehavior(flyBehavior: () -> Unit){
        this.flyBehavior = flyBehavior
    }

    abstract fun display()

}

class MallardDuck: Duck(::quack, FlyWithWings()::fly, ::danceWaltz){

    override fun display() {
        println("I'm mallard duck")
    }

}

class RedHeadDuck: Duck(::quack, FlyWithWings()::fly, ::danceMinuet){

    override fun display() {
        println("I'm Redhead duck")
    }

}

class DecoyDuck: Duck(::muteQuack, ::flyNoWay, ::doesNotDance) {

    override fun display() {
        println("I'm decoy duck")
    }

}

class RubberDuck: Duck(::squeak, ::flyNoWay, ::doesNotDance){

    override fun display() {
        println("I'm rubber duck")
    }

}

class ModelDuck: Duck(::quack, ::flyNoWay, ::doesNotDance){

    override fun display() {
        println("I'm model duck")
    }

}

fun quack() {
    println("quack, quack")
}

fun squeak() {
    println("Squeek!!!")
}

fun muteQuack() {}

interface FlyBehavior{
    fun fly()
}

class FlyWithWings: FlyBehavior {

    var flyCount = 0

    override fun fly() {
        flyCount++
        println("I'm flying with wings for the $flyCount'th time")
    }

}

fun flyNoWay(){}

fun danceWaltz(){
    println("I'm dance waltz")
}

fun danceMinuet(){
    println("I'm dance minuet")
}

fun doesNotDance(){}
