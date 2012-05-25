package org.willyfrog.algorimica.grafo

import org.willyfrog.algorimica.grafo.Nodo
import com.badlogic.gdx.graphics.{VertexAttribute, Mesh}
import com.badlogic.gdx.graphics.VertexAttributes.Usage

/**
 * Created with IntelliJ IDEA.
 * User: guillermo
 * Date: 25/05/12
 * Time: 9:51
 * To change this template use File | Settings | File Templates.
 */

class Arista(val origen: Vertice, val destino: Vertice, nodos: Array[Nodo] = Array[Nodo]()) {
  var mesh:Mesh = null
  def listaCoordenadasNodos():List[Float]={    //Goes through nodes and append coordinates as a list
    return (for (n <- nodos) yield n.coordenadas()).toList.flatten
  }
  def getVertexList:List[Float] ={
    return origen.coordenadas:::listaCoordenadasNodos():::destino.coordenadas
  }
  def getMesh():Mesh={
    val len = 2 + nodos.length
    if (mesh == null){
      mesh = new Mesh(true, len, len, new VertexAttribute(Usage.Position, len, "a_position"))
      mesh.setVertices(getVertexList.toArray)

    }
    return mesh
  }
}
