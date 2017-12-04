package com.org.textReplacer

import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.layout.StackPane
import javafx.scene.control.Label
import javafx.scene.Scene

class StartApp extends Application { 
  
  
  def start(primaryStage: Stage) {
    
    primaryStage.setTitle("Lalal")
    
    val root = new StackPane
    root.getChildren.addAll(new Label("Hello world"))
    
    primaryStage.setScene(new Scene(root,400,300))
    primaryStage.show()
    
    
  }
}

object StartApp {
   def main(args: Array[String]): Unit = {
    Application.launch(classOf[StartApp], args:_*)
  }
}