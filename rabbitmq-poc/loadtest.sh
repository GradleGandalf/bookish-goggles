#!/bin/bash
# simple load test using hey (https://github.com/rakyll/hey)
# requires hey installed in the environment

echo "Running load test..."
hey -n 4000 -c 100 -q 200 -m POST -H 'Content-Type: application/json' -d '{"content":"test"}' http://localhost:8080/api/messages
