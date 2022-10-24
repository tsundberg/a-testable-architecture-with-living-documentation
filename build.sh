#!/bin/bash

time (
  ./mvnw clean
  rc=$?
  if [ $rc -ne 0 ]; then
    exit $rc
  fi

  ./mvnw test -Dtest=!RunCucumberTest,!SqlTaskRepositoryIT
  rc=$?
  if [ $rc -ne 0 ]; then
    exit $rc
  fi

  ./mvnw test -Dseam=service -Dtest=RunCucumberTest
  rc=$?
  if [ $rc -ne 0 ]; then
    exit $rc
  fi

  ./mvnw test -Dseam=database -Dtest=RunCucumberTest
  rc=$?
  if [ $rc -ne 0 ]; then
    exit $rc
  fi

  ./mvnw install
)
