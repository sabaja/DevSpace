package com.spark.core;
import static spark.Spark.*;

public class FristExampleSpark {

	public static void main(String[] args) {
		get("/hello", (req, res) -> "Hello world");;

	}

}
