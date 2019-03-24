#!/bin/bash

echo "Compiling..."

javac ./CalculatorInterpreter.java ./ParseError.java

echo "Running success examples..."

echo "****************************"
echo "Running expression:"
cat ./examples/easy_success
echo ""
echo "----------------------------"
java CalculatorInterpreter < ./examples/easy_success

echo "****************************"
echo "Running expression:"
cat ./examples/normal_success
echo ""
echo "----------------------------"
java CalculatorInterpreter < ./examples/normal_success

echo "****************************"
echo "Running expression:"
cat ./examples/hard_success
echo ""
echo "----------------------------"
java CalculatorInterpreter < ./examples/hard_success

echo "****************************"
echo "Running expression:"
cat ./examples/extreme_success
echo ""
echo "----------------------------"
java CalculatorInterpreter < ./examples/extreme_success


echo "Running fail examples..."

echo "****************************"
echo "Running expression:"
cat ./examples/easy_fail
echo ""
echo "----------------------------"
java CalculatorInterpreter < ./examples/easy_fail

echo "****************************"
echo "Running expression:"
cat ./examples/hard_fail
echo ""
echo "----------------------------"
java CalculatorInterpreter < ./examples/hard_fail