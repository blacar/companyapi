# Company API
[![Build Status](https://travis-ci.org/blacar/companyapi.svg?branch=master)](https://travis-ci.org/blacar/companyapi)

A rest API exposing Company and Owner resources through standard REST endpoints.

This is using Spring MVC running on a spring-boot application.
This is using in-memory H2 database since seems enough for the expected workload

Features:

- CI made with travis
- Code quality -> Using checkstyle, findbugs, and JaCoCo
- Allow to build docker image to local testing

TODO:
- More tests
- Tweak logging
- JavaDoc
- Better API documentation
- Publish into dockerHub
- CD into cloud from travis

Usage:

mvn clean install [docker:build]
java -jar ./target/companyapi-0.0.1-SNAPSHOT.jar

API DESCRIPTION

Get All Companies:
curl -X GET http://127.0.0.1:8080/company

Create New Company:
curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"company1\", \"country\":\"Spain\", \"city\":\"city1\", \"phone\":\"0034666666666\"}" http://127.0.0.1:8080/company

Update Company Name:
curl -X PUT -H "Content-Type: application/json" -d "{\"name\":\"company21\", \"country\":\"Spain\", \"city\":\"city1\", \"phone\":\"0034666666666\"}" http://127.0.0.1:8080/company/1

Create New Owner:
curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"owner1\"}" http://127.0.0.1:8080/owner

Add owner to company:
curl -X PUT http://127.0.0.1:8080/company/1/owner/1

## Considerations

### Security

Suggest using Spring Security bundle for spring-boot.
I like stateless JWT based security. No sessions made a better REST.

### Redundancy

We can trigger a new API and use a load balancer in front of them

Problem with the actual implementation is that H2 in memory is not shared
so we will need to extract DB to a external service that could be consumed by both APIs. 

 


