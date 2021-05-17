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

import static org.junit.Assert.*;

import java.util.Optional;

import org.apache.commons.rdf.api.AbstractDatasetTest;
import org.apache.commons.rdf.api.RDF;
import org.junit.Assume;
import org.junit.Test;
import org.apache.commons.rdf.api.*;

/**
 * Test SimpleRDF with AbstractGraphTest
 */
public class SimpleDatasetTest extends AbstractDatasetTest {

    @Override
    public RDF createFactory() {
        return new SimpleRDF();
    }

    @Test
    public void datasetToString() {
        Assume.assumeNotNull(aliceName, companyName);
        //System.out.println(dataset);
        assertTrue(
                dataset.toString().contains("<http://example.com/alice> <http://xmlns.com/foaf/0.1/name> \"Alice\" <http://example.com/graph1> ."));
        assertTrue(dataset.toString().contains(" <http://xmlns.com/foaf/0.1/name> \"A company\" _:"));
        assertTrue(dataset.toString().contains("<http://example.com/alice> <http://xmlns.com/foaf/0.1/isPrimaryTopicOf> <http://example.com/graph1> ."));

    }

    /**
     * Test what happens if 11 quad is in the dataset and you call toString
     */
    @Test
    public void DatasetImpToStringTest() {
        IRI randomIri = factory.createIRI("somefullrandomiri");
        bobName = factory.createLiteral("Bob", "en-US");
        Literal randomLiteralValue = factory.createLiteral("literal for eleventh");
        // Adding 11th quad to dataset
        dataset.add(null, alice, randomIri, randomLiteralValue);

        //Assert
        assertTrue(dataset.toString().endsWith("more"));
        assertEquals(11, dataset.size());
    }

    /**
     * Test if DummyIRI converts into IRIImpl
     */
    @Test
    public void test_internallyMap_IRI_to_IRIImpl() {
        Quad quad = factory.createQuad(null, alice, new DummyIRI(5), alice);
        dataset.add(quad);
        assertEquals(1, dataset.stream(Optional.empty(), alice, null, alice).count());
        Quad foundQuad = dataset.stream(Optional.empty(), alice, null, alice).findFirst().get();
        assertTrue(foundQuad.getPredicate() instanceof IRIImpl);
    }

    /**
     * Test if other Literal implementation converts to LiteralImpl with Language tag
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

        Quad quad = factory.createQuad(null,alice,alice,otherLiteral);
        dataset.add(quad);
        assertEquals(1, dataset.stream(Optional.empty(), alice, alice, null).count());
        Quad foundQuad = dataset.stream(Optional.empty(), alice, alice, null).findFirst().get();
        assertTrue(foundQuad.getObject() instanceof LiteralImpl);
    }


    /**
     * Test if other Literal implementation converts to LiteralImpl
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
                return factory.createIRI("asd");
            }
        };

        Quad quad = factory.createQuad(null,alice,alice,otherLiteral);
        dataset.add(quad);
        assertEquals(1, dataset.stream(Optional.empty(), alice, alice, null).count());
        Quad foundQuad = dataset.stream(Optional.empty(), alice, alice, null).findFirst().get();
        assertTrue(foundQuad.getObject() instanceof LiteralImpl);
    }
}
