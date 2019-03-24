#!/bin/bash
echo "Compiling..."
javac ./CalculatorInterpreter.java ./ParseError.java
FILES="./examples/easy_fail
./examples/easy_success
./examples/extreme_fail
./examples/extreme_fail2
./examples/extreme_success
./examples/extreme_success2
./examples/hard_fail
./examples/hard_fail2
./examples/hard_success
./examples/hard_success2
./examples/hard_success3
./examples/normal_success"
for input in $FILES
do
    echo ""
    echo "============================"
    cat $input
    echo ""
    echo "----------------------------"
    java CalculatorInterpreter < $input
    echo "============================"
done