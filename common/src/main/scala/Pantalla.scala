package org.willyfrog.algorimica

import com.badlogic.gdx.{Gdx, ApplicationListener}
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import collection.mutable.ArrayBuffer
import com.badlogic.gdx.Input.Buttons
import com.badlogic.gdx.graphics._

class Pantalla extends ApplicationListener {
  var cam:OrthographicCamera = null
  var grid:Mesh = null
  var unprojectedVertex:Vector3 = new Vector3()
  var lineVertices:ArrayBuffer[Float] = null

  val MAX_LINES:Int = 1000

  def needsGL20():Boolean = false

  override def create() {
    cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
    lineVertices = new ArrayBuffer[Float]()
    grid = new Mesh(false, MAX_LINES * 2, 0, new VertexAttribute(Usage.Position, 2, "a_pos"));
  }
  override def render() {
    Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f)
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT)

    cam.update()
    cam.apply(Gdx.gl10) //TODO: buscar qué hace esta linea!

    if (lineVertices.length >= 4)
      //grid.render(GL10.GL_LINE_STRIP)
      grid.render(GL20.GL_LINE_STRIP)

    if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Buttons.LEFT)){
      unprojectedVertex.set(Gdx.input.getX(),Gdx.input.getY(), 0 )
      cam.unproject(unprojectedVertex)
      if (lineVertices.length == 0 || (unprojectedVertex.dst(lineVertices(lineVertices.length - 2), lineVertices(lineVertices.length - 1), 0) > 10)) {
        lineVertices.append(unprojectedVertex.x, unprojectedVertex.y)
        grid.setVertices(lineVertices.toArray, 0, lineVertices.length)
      }
    }

  }
  override def dispose() {}
  override def pause() {}
  override def resume() {}
  override def resize(x: Int, y: Int) {}
}
