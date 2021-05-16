package org.apache.commons.rdf.simple;
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.apache.commons.rdf.api.Dataset;
import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.Literal;
import org.apache.commons.rdf.api.Quad;
import org.apache.commons.rdf.api.RDFTerm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

//Just using the library
public class ExploratoryTesting {
	SimpleRDF factory;
	Dataset dataset;
	Graph graph;
	
	IRI graphname ;
	IRI tonyS ;
	IRI chrisM ;
	IRI chrisB ;
	IRI tonyM ;
	IRI knows ;
	IRI firstName ;
	IRI lastName ;
	Literal tonyName ;
	Literal chrisName ;
	Literal baleName ;
	Literal moltisantiName ;
	Literal montanaName ;
	Literal sopranoName ;
	Quad tonySFNameQuad ;
	Quad tonySLNameQuad ;
	Quad tonyMFNameQuad ;
	Quad tonyMLNameQuad ;
	Quad chrisBFNameQuad ;
	Quad chrisBLNameQuad ;
	Quad chrisMFNameQuad ;
	Quad chrisMLNameQuad ;
	Quad tonyKnowsChris;
	
	@Before
	public void createStatements() {
		factory = new SimpleRDF();
		dataset = factory.createDataset();
		graph = factory.createGraph();
		
		//fill dataset with some quads
		graphname = factory.createIRI("http://exploratory.hu/graphs/1");
		tonyS = factory.createIRI("http://exploratory.com/characters/1");
		chrisM = factory.createIRI("http://exploratory.com/characters/2");
		chrisB = factory.createIRI("http://exploratory.com/characters/3");
		tonyM = factory.createIRI("http://exploratory.com/characters/4");
		knows = factory.createIRI("http://xmlns.com/foaf/0.1/knows");
		firstName = factory.createIRI("http://xmlns.com/foaf/0.1/firstname");
		lastName = factory.createIRI("http://xmlns.com/foaf/0.1/lastname");
		tonyName = factory.createLiteral("Tony");
		chrisName = factory.createLiteral("Christian");
		baleName = factory.createLiteral("Bale");
		moltisantiName = factory.createLiteral("Moltisanti");
		montanaName = factory.createLiteral("Montana");
		sopranoName = factory.createLiteral("Soprano");
		tonySFNameQuad = factory.createQuad(graphname, tonyS, firstName, tonyName);
		tonySLNameQuad = factory.createQuad(graphname, tonyS, lastName, sopranoName);
		tonyMFNameQuad = factory.createQuad(graphname, tonyM, firstName, tonyName);
		tonyMLNameQuad = factory.createQuad(graphname, tonyM, lastName, montanaName);
		chrisBFNameQuad = factory.createQuad(graphname, chrisB, firstName, chrisName);
		chrisBLNameQuad = factory.createQuad(graphname, chrisB, lastName, baleName);
		chrisMFNameQuad = factory.createQuad(graphname, chrisM, firstName, chrisName);
		chrisMLNameQuad = factory.createQuad(graphname, chrisM, lastName, chrisName);
		tonyKnowsChris = factory.createQuad(graphname, tonyS, knows, chrisM);
		
		//1)
		dataset.add(tonySFNameQuad);
		dataset.add(tonySLNameQuad);
		dataset.add(chrisMFNameQuad);
		dataset.add(chrisMLNameQuad);
		dataset.add(tonyKnowsChris);
		dataset.add(tonyMFNameQuad);
		dataset.add(tonyMLNameQuad);
		dataset.add(chrisBLNameQuad);
		dataset.add(chrisBFNameQuad);
		printDataset(); //select all OK
		
		//4)
		graph.add(tonyS, firstName, tonyName);
		graph.add(tonyS, lastName, sopranoName);
		graph.add(tonyM, firstName, tonyName);
		graph.add(tonyM, lastName, montanaName);
		graph.add(chrisB, firstName, chrisName);
		graph.add(chrisB, lastName, baleName);
		graph.add(chrisM, firstName, chrisName);
		graph.add(chrisM, lastName, chrisName);
		graph.add(tonyS, knows, chrisM);
		printGraph();
		
		//2)
		System.out.println("Quads where first name is Tony");
		for(Quad t: dataset.iterate(null, null, firstName, (RDFTerm) tonyName)) {
			System.out.println(t.toString());
		}
		
		//3)
		System.out.println("Full names of the Tonys from dataset");
		for(Quad t: dataset.iterate(null, null, firstName, (RDFTerm) tonyName)) {
			System.out.print(tonyName.ntriplesString() + " ");
			for(Quad q: dataset.iterate(null, t.getSubject(), lastName, null)) {
				System.out.println(q.getObject().ntriplesString());
			}
		}
		
		//7)
		System.out.println("Full names of the Tonys from graph");
		graph.stream(null, firstName, tonyName).forEach(t -> {
			System.out.print(t.getObject());
			graph.stream(t.getSubject(),lastName,null).forEach(t2 -> System.out.print(t2.getObject()));
		});
		
	}
	
	@Test
	public void compareGraphVsDatasetSize() {	
		//5)
		assertEquals(graph.size(),dataset.size());
	}
	
	@Test
	public void checkAddedInBoth() {
		//6)
		assertTrue(dataset.contains(tonyKnowsChris));
		assertTrue(graph.contains(tonyS, knows, chrisM));
		assertFalse(graph.contains(factory.createTriple(chrisB, knows, tonyM)));
		assertFalse(dataset.contains(factory.createQuad(graphname, chrisB, knows, tonyM)));
	}
	

	private void printDataset() {
		System.out.println("Full dataset:");
     	for (Quad t : dataset.iterate()) {
     		System.out.println(t.toString());
     	}
	}
	
	private void printGraph() {
		System.out.println("Full graph:");
     	graph.stream().forEach(t -> System.out.println(t.toString()));
	}
}
