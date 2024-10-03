# AI Hasher

## Required to compile

### Ollama

You will need Ollama running locally on port 11434 to compile using maven.

You will also need to use the [Modelfile](../Modelfile) to create an LLM Model named 'hasher' using the command `ollama create hasher -f Modelfile`.

### Java 

Java version 17

You will need to update the tests for the hasher to work with the Modelfile since each one is based on your model and seed. In this project we have hardcoded them so don't use this in production...please.

## Compiling

run `mvn clean compile package` to compile and create a jar file that includes all of the libaries needed to run the hasher.

## Install into FusionAuth

Follow the instructions at [FusionAuth](https://fusionauth.io/docs/extend/code/password-hashes/writing-a-plugin#install-the-plugin) to make the ai hasher available to FusionAuth.

Bonus points if you show this to your security team and they cry.
