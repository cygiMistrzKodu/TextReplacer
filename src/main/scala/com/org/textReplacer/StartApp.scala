package com.org.textReplacer

import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.layout.StackPane
import javafx.scene.control.Label
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.control.TextArea
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.RowConstraints
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.geometry.Pos
import javafx.geometry.Insets
import javafx.scene.layout.VBox
import javafx.scene.input.TransferMode
import javafx.event.EventType
import javafx.scene.input.DragEvent
import javafx.event.ActionEvent
import javafx.event.EventHandler
import scala.collection.JavaConverters._
import scala.collection.JavaConverters._


class StartApp extends Application {

  def start(primaryStage: Stage) {

    var searchFor = ""
    var replace = ""
    var inputText = ""

    primaryStage.setTitle("Text Replacer")

    val root = new BorderPane
    
    
    val outputTexLabel = new Label
    outputTexLabel.setText("Result Text")
    val outputTextArea = new TextArea

    val inputTextLabel = new Label
    inputTextLabel.setText("insert Text")
    val inputTextArea = new TextArea

    inputTextArea.textProperty().addListener(event => {
    	
    	inputText = inputTextArea.getText
    			
    			outputTextArea.setText(getTransormedText(new InputGatherer(searchFor, replace, inputText)))
    			
    })
    
   
    
    inputTextArea.setOnDragOver(new EventHandler[DragEvent]  {
      
     override  def handle( e : DragEvent) {
       
    	 val copy = TransferMode.COPY
    	 
//    	 for (t <- ts) {
//    	   println(t)
//    	 }
    	 
       e.acceptTransferModes(copy)
       
       
//       println(ts)
        
      }
      
    })
    
    inputTextArea.setOnDragDropped(event => {
       
      if (event.getDragboard.hasFiles()){
       
        val files =  event.getDragboard.getFiles.asScala
        
        var filesNamesTogether = "";
        
        for (f <- files ) {
          
          val fileName = f.getName
          val dotPosition = fileName.lastIndexOf(".")
          
          if(dotPosition > 0) {
            
            val fileNameWithoutExtenstion = fileName.substring(0,dotPosition)
            
            filesNamesTogether += fileNameWithoutExtenstion +"\r\n"
            
          }
         
          
        }
        
        filesNamesTogether = filesNamesTogether.trim()
        
        inputTextArea.setText(filesNamesTogether)
        
        
      }
      
    })
    
    
    

    val refreshButton = new Button

    val searchLabel = new Label
    searchLabel.setText("search for: ")
    val searchInputTextField = new TextField
    searchInputTextField.textProperty().addListener(event => {

      searchFor = searchInputTextField.getText

      outputTextArea.setText(getTransormedText(new InputGatherer(searchFor, replace, inputText)))

    })

    val replaceLabel = new Label
    replaceLabel.setText("replace: ")
    val replaceInputTextField = new TextField
    replaceInputTextField.textProperty().addListener(event => {

      replace = replaceInputTextField.getText

      outputTextArea.setText(getTransormedText(new InputGatherer(searchFor, replace, inputText)))

    })

    val searchReplacePane = new HBox
    searchReplacePane.setPadding(new Insets(15, 12, 15, 12))
    searchReplacePane.setSpacing(10)
    searchReplacePane.setAlignment(Pos.TOP_CENTER)
    searchReplacePane.getChildren.setAll(searchLabel, searchInputTextField, replaceLabel, replaceInputTextField)

    val textTransfomationPane = new VBox
    textTransfomationPane.setAlignment(Pos.TOP_CENTER)
    textTransfomationPane.getChildren.setAll(inputTextLabel, inputTextArea, outputTexLabel, outputTextArea)

    root.setTop(searchReplacePane)
    root.setCenter(textTransfomationPane)

    primaryStage.setMaxHeight(500)

    primaryStage.setScene(new Scene(root, 500, 700))
    primaryStage.show()

  }
  
  private def getTransormedText( inputGatherer : InputGatherer) : String = {
    
     new TextReplacer(inputGatherer).replace
     
  }
  
  
  
}

object StartApp {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[StartApp], args: _*)
  }
}