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
import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.Literal;
import org.apache.commons.rdf.api.Quad;
import org.apache.commons.rdf.api.RDFTerm;

import static org.junit.Assert.*;

//Just using the library
public class ExploratoryTesting {
	public static SimpleRDF factory;
	public static Dataset dataset;
	
	public static void main(String[] args) {
		factory = new SimpleRDF();
		dataset = factory.createDataset();
		//fill dataset with some quads
		IRI graph = factory.createIRI("http://exploratory.hu/graphs/1");
		IRI tonyS = factory.createIRI("http://exploratory.com/characters/1");
		IRI chrisM = factory.createIRI("http://exploratory.com/characters/2");
		IRI chrisB = factory.createIRI("http://exploratory.com/characters/3");
		IRI tonyM = factory.createIRI("http://exploratory.com/characters/4");
		IRI knows = factory.createIRI("http://xmlns.com/foaf/0.1/knows");
		IRI firstName = factory.createIRI("http://xmlns.com/foaf/0.1/firstname");
		IRI lastName = factory.createIRI("http://xmlns.com/foaf/0.1/lastname");
		Literal tonyName = factory.createLiteral("Tony");
		Literal chrisName = factory.createLiteral("Christian");
		Literal baleName = factory.createLiteral("Bale");
		Literal moltisantiName = factory.createLiteral("Moltisanti");
		Literal montanaName = factory.createLiteral("Montana");
		Literal sopranoName = factory.createLiteral("Soprano");
		Quad tonySFNameQuad = factory.createQuad(graph, tonyS, firstName, tonyName);
		Quad tonySLNameQuad = factory.createQuad(graph, tonyS, lastName, sopranoName);
		Quad tonyMFNameQuad = factory.createQuad(graph, tonyM, firstName, tonyName);
		Quad tonyMLNameQuad = factory.createQuad(graph, tonyM, lastName, montanaName);
		Quad chrisBFNameQuad = factory.createQuad(graph, chrisB, firstName, chrisName);
		Quad chrisBLNameQuad = factory.createQuad(graph, chrisB, lastName, baleName);
		Quad chrisMFNameQuad = factory.createQuad(graph, chrisM, firstName, chrisName);
		Quad chrisMLNameQuad = factory.createQuad(graph, chrisM, lastName, chrisName);
		Quad tonyKnowsChris = factory.createQuad(graph, tonyS, knows, chrisM);
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
		printGraph(); //select all OK
		
		//2)
		System.out.println("Quads where first name is Tony");
		for(Quad t: dataset.iterate(null, null, firstName, (RDFTerm) tonyName)) {
			System.out.println(t.toString());
		}
		
		//3)
		System.out.println("Full names of the Tonys");
		for(Quad t: dataset.iterate(null, null, firstName, (RDFTerm) tonyName)) {
			System.out.print(tonyName.ntriplesString() + " ");
			for(Quad q: dataset.iterate(null, t.getSubject(), lastName, null)) {
				System.out.println(q.getObject().ntriplesString());
			}
		}
		
	}

	private static void printGraph() {
		System.out.println("Full graph:");
     	for (Quad t : dataset.iterate()) {
     		System.out.println(t.toString());
     	}
	}
}
