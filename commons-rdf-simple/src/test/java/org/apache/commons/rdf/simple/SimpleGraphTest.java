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
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.apache.commons.rdf.api.*;
import org.junit.Assume;
import org.junit.Test;

/**
 * Test SimpleRDF with AbstractGraphTest
 */
public class SimpleGraphTest extends AbstractGraphTest {

    @Override
    public RDF createFactory() {
        return new SimpleRDF();
    }

    @Test
    public void graphToString() {
        Assume.assumeNotNull(aliceName, companyName);
        // System.out.println(graph);
        assertTrue(
                graph.toString().contains("<http://example.com/alice> <http://xmlns.com/foaf/0.1/name> \"Alice\" ."));
        assertTrue(graph.toString().contains(" <http://xmlns.com/foaf/0.1/name> \"A company\" ."));

    }
    
    /**
     * Test if dataset converts other Literal implementation to LiteralImpl
     */
    @Test
    public void test_internallyMap_Literal_to_LiteralImp() {
        final Literal otherLiteral = new Literal() {
            @Override
            public String ntriplesString() {
                return "Hello@eN-Gb";
            }
            @Override
            public String getLexicalForm() {
                return "Hello";
            }
            @Override
            public Optional<String> getLanguageTag() {
                return Optional.empty();
            }
            @Override
            public IRI getDatatype() {
                return factory.createIRI("randomstuff");
            }
        };

        Triple triple = factory.createTriple(alice,alice,otherLiteral);
        graph.add(triple);
        assertEquals(1, graph.stream( alice, alice, null).count());
        Triple foundTriple = graph.stream(alice, alice, null).findFirst().get();
        assertTrue(foundTriple.getObject() instanceof LiteralImpl);
    }

    /**
     * Test if dataset converts other Literal implementation to LiteralImpl with Language tag
     */
    @Test
    public void test_internallyMap_Literal_to_LiteralImp_WithLanguageTag() {
        final Literal otherLiteral = new Literal() {
            @Override
            public String ntriplesString() {
                return "Hello@eN-Gb";
            }
            @Override
            public String getLexicalForm() {
                return "Hello";
            }
            @Override
            public Optional<String> getLanguageTag() {
                return Optional.of("eN-Gb");
            }
            @Override
            public IRI getDatatype() {
                return factory.createIRI("asd");
            }
        };

        Triple triple = factory.createTriple(alice,alice,otherLiteral);
        graph.add(triple);
        assertEquals(1, graph.stream( alice, alice, null).count());
        Triple foundTriple = graph.stream(alice, alice, null).findFirst().get();
        assertTrue(foundTriple.getObject() instanceof LiteralImpl);
    }

    /**
     * Test if dataset converts other IRI implementation to IRIImpl
     */
    @Test
    public void test_internallyMap_IRI_to_IRIImpl() {
        Triple triple = factory.createTriple(alice, new DummyIRI(5), alice);
        graph.add(triple);
        assertEquals(1, graph.stream(alice, null, alice).count());
        Triple foundQuad = graph.stream(alice, null, alice).findFirst().get();
        assertTrue(foundQuad.getPredicate() instanceof IRIImpl);
    }
    
    /**
     * Test what happens if the graph contains 11 elements and call toString
     */
    @Test
    public void GraphImplToStringTest() {
        IRI randomIri = factory.createIRI("randomiri1");
        IRI randomIri2 = factory.createIRI("randomiri2");
        IRI randomIri3 = factory.createIRI("randomiri3");
        bobName = factory.createLiteral("Bob", "en-US");
        Literal randomLiteralValue = factory.createLiteral("randomstuff");
        
        graph.add(alice, randomIri, randomLiteralValue);
        graph.add(alice, randomIri2, randomLiteralValue);
        graph.add(alice, randomIri3, randomLiteralValue);
        
        assertEquals(11, graph.size());
        assertTrue(graph.toString().endsWith("more"));
    }

}
