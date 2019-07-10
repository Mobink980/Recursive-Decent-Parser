# Recursive-Decent-Parser
A Simple Top Down Parser written in java.
In this project we use recursive decent parsing, to parse the strings produced by a grammar.
This is how it works: 
For each variable we have a function which is usually recursive, and we also have a match function. In the match function, we take an input which is the token determined by syntax, and in the function structure, the current token which is the last token read by the scanner will be compared with the syntax token.
