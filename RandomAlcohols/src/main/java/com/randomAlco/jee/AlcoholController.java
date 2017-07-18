package com.randomAlco.jee;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Controller
public class AlcoholController {

	private static MongoOperations mongoOperation;
	static int numberLimit;
	public Random rand;
	public static List<Float> typesOptions = new ArrayList<Float>();

	public static MongoOperations getMongoOperations(String type) throws Exception {
		MongoClientURI uri = new MongoClientURI("mongodb://anyone:secret@ds159892.mlab.com:59892/alcohol");
		MongoClient client = new MongoClient(uri);
		mongoOperation = new MongoTemplate(client, "alcohol");
		Query query= new Query();
		if(type == null){
		numberLimit = (int) mongoOperation.count(query, "Alcohol");
		}else{
			query.addCriteria(Criteria.where("type").is(type));
			query.fields().include("num");
			typesOptions = mongoOperation.find(query, Float.class, "Alcohol");
		}
		return mongoOperation;
	}

	@RequestMapping(value = "/")
	public String homePage() {
		return "index";
	}

	@RequestMapping(value = "/random", method = RequestMethod.POST)
	public @ResponseBody String random(@RequestBody String type) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		getMongoOperations(type);
		int random = 1;
		Alcohol alco = mongoOperation.findOne(Query.query(Criteria.where("type").is(type).and("num").is(random)), Alcohol.class, "Alcohol");
		return mapper.writeValueAsString(alco);
	}

	@RequestMapping(value = "/random")
	public @ResponseBody String random() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		getMongoOperations(null);
		rand = new Random();
	    int random = (int) rand.nextInt(numberLimit) + 1;
		Alcohol alco = mongoOperation.findOne(Query.query(Criteria.where("num").is(random)), Alcohol.class, "Alcohol");
		return mapper.writeValueAsString(alco);
	}
}
