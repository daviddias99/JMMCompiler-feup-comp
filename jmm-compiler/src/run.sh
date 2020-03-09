#!/bin/bash
FILES=../test/*

function usage() {
cat << EOF
Usage: ${0##*/} [-hvc]
Runs all the scripts in the $FILES folder

    -h          display this help and exit
    -c          compile Parser.jj program
    -v          verbose mode. Can be used to keep track
                of the process.
EOF
}

function test_error() {
    "$@" 2> /dev/null
    local status=$?
    if [ $status -ne 0 ]; then
        echo " > Error with $(basename $3)"
    else
        if [ "$verbose" = "1" ]; then
            echo " > Success with $(basename $3)"
        fi    
    fi
    return $status
}

function compile() {
    jjtree Parser.jjt
    javacc Parser.jj
    javac *.java
}

### MAIN
verbose=0

while [ "$1" != "" ]; do    
    case $1 in
        -h | -\? | --help)
            usage
            exit 0
            ;;
        -v | --verbose)  verbose=1
            ;;
        -c | --compile) 
            compile
            ;;
        *)  
            usage
            exit 1
    esac
    shift
done

for f in $FILES
do
    if [ "$verbose" = "1" ]; then
        echo "Processing $(basename $f) file..."
    fi
    test_error java Compiler $f
done
