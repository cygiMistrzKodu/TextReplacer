package com.org.textReplacer

class TextReplacer(inputGatherer: InputGatherer) {

  val InputGatherer: InputGatherer = inputGatherer

  def replace: String = {

    inputGatherer.inputText.replace(inputGatherer.searchFor, inputGatherer.replace)
   
  }

}