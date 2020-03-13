package com.auru.mobilunity.sharedData

import com.auru.mobilunity.dto.RepoElement

const val testRepoElementsResult1 =
    "{"+
            "\"name\": \"yajl-objc\","+
            "\"owner\": {"+
            "\"login\": \"square\""+
            "},"+
            "\"description\": \"Objective-C bindings for YAJL (Yet Another JSON Library) C library\","+
            "\"fork\": true"+
            "}"

const val testRepoElementsResult2 = "["+
        "{"+
        "\"name\": \"yajl-objc\","+
        "\"owner\": {"+
        "\"login\": \"square\""+
        "},"+
        "\"description\": \"Objective-C bindings for YAJL (Yet Another JSON Library) C library\","+
        "\"fork\": true"+
        "},"+
        "{"+
        "\"name\": \"simplerrd\","+
        "\"owner\": {"+
        "\"login\": \"square\""+
        "},"+
        "\"description\": \"SimpleRRD provides a simple Ruby interface for creating graphs with RRD\","+
        "\"fork\": false"+
        "}]"

class RepoElementsTestData {
    companion object{
        val expectedElement1 = RepoElement(
            "yajl-objc",
            "Objective-C bindings for YAJL (Yet Another JSON Library) C library"
        )
        val expectedElement2 = RepoElement(
            "simplerrd",
            "SimpleRRD provides a simple Ruby interface for creating graphs with RRD"
        )
    }
}