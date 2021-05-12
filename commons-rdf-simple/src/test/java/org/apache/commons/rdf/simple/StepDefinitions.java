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
package org.apache.commons.rdf.simple;

import static org.junit.Assert.assertEquals;

import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.Literal;

import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	
    private SimpleRDF factory = new SimpleRDF();
    private GraphImpl graph;
    private String isPicasso;
    private TripleImpl anythingTriple;
    private TripleImpl picassoTriple;
    private TripleImpl monetTriple;
    
    @Given("If only {string} is added to the graph")
    public void monetAddedtotheGraph(String artist) {
       graph = (GraphImpl) factory.createGraph();
       final IRI monetNameIRI = factory.createIRI("http://cucumber.hu/festo/+artist+/");
       final IRI predicate = factory.createIRI("is");
       final Literal literal = factory.createLiteral(artist);
       monetTriple = (TripleImpl) factory.createTriple(monetNameIRI, predicate, literal);
       final IRI picassoNameIRI = factory.createIRI("http://cucumber.hu/festo/+artist+/");
       final IRI predicate2 = factory.createIRI("is");
       final Literal literal2 = factory.createLiteral("Picasso");
       picassoTriple = (TripleImpl) factory.createTriple(picassoNameIRI, predicate2, literal2);
       final IRI anythingIRI = factory.createIRI("http://cucumber.hu/festo+artist+/");
       final IRI predicate3 = factory.createIRI("is");
       final Literal literal3 = factory.createLiteral("Picasso");
       anythingTriple = (TripleImpl) factory.createTriple(anythingIRI, predicate3, literal3);
       
	       switch(artist)
	       {
		       case "Monet":
		    	   graph.add(monetTriple);
		    	   break;
		       case "Picasso":
		    	   graph.add(picassoTriple);
		    	   break;
		       case "anything else!":
		    	   graph.add(anythingTriple);
		    	   break;
	       }
       }

    @When("we want to know if Picasso is in the graph")
    public void is_picasso() {
        isPicasso = graph.contains(this.picassoTriple) ? "true": "false";
    }
    
    
    @Then("we should not be suprised that Graph contains Picasso is {string}")
    public void we_should_be_told(String answer) {
        assertEquals(answer, isPicasso);
    }
    
    private DatasetImpl dataSet;
    private int cntShouldBe = 0;
    private QuadImpl quadtoRemove;
    
    @Given("We put {int} quads in the graph")
    public void putting_Quads(int numberofQuads) {
    	this.dataSet = new DatasetImpl(this.factory);
    	cntShouldBe = numberofQuads;
    	String literals[] = new String[] {"sg","anything","everything","almost everything","nothing","barely nothing"};
    	final IRI someIRI = factory.createIRI("http://cucumber.hu/random/doesntmatter");
        
        final IRI anotherPredicate = factory.createIRI("is");
        final Literal anotherLiteral = factory.createLiteral("somethingelse");
      
        quadtoRemove = new QuadImpl(factory.createBlankNode("uresNode2"),someIRI,anotherPredicate,anotherLiteral);
        for (int i =0; i< numberofQuads-1;i++)
        	{
        	factory.createIRI("is");
        	final IRI newIRI = factory.createIRI("http://cucumber.hu/random/doesntmatter");
            final IRI somePredicate = factory.createIRI("is");
            final Literal someLiteral = factory.createLiteral(literals[i]);
        	dataSet.add(new QuadImpl(factory.createBlankNode("uresNode"),newIRI,somePredicate,someLiteral));
        	}
        dataSet.add(quadtoRemove);
       }
    
    @When("we ask the count of the quads in graph is equal to that")
    public void graph_count_equals() {
    	cntShouldBe = (int) dataSet.size();
    }
    
    
    
    @But("if we remove a quad from the graph")
    public void remove_quad() {
    	dataSet.remove(quadtoRemove);
    	cntShouldBe-=1;
    }
    
    @Then("the graphs count should be {int}")
    public void graph_count(int answerCount) {
        assertEquals(cntShouldBe, answerCount);
    }
    
    
    
    
    
}