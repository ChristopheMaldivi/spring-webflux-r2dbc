#!/bin/sh

docker run --rm -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_DB=mytestdb  postgres
