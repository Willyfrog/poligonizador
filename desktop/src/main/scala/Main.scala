package org.willyfrog.algorimica

import com.badlogic.gdx.backends.lwjgl.LwjglApplication

import com.badlogic.gdx._
//import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.GL10

object Main {
  def main(args: Array[String]): Unit = {
    new LwjglApplication(new Pantalla(), "Poligonizador", 480, 320, false)
  }
}
