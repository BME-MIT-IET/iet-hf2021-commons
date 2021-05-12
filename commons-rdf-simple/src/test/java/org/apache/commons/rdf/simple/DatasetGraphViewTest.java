package org.apache.commons.rdf.simple;
import static org.junit.Assert.*;
import org.apache.commons.rdf.api.*;
import org.junit.Before;
import org.junit.Test;

public class DatasetGraphViewTest {

    private DatasetGraphView datasetGraphView;
    private RDF factory;
    private DatasetGraphView unionDataset;
    IRI jack;
    IRI hates;
    IRI kevin;

    @Before
    public void initDatasetGraphView() {
        factory = new SimpleRDF();
        Dataset dataset = factory.createDataset();
     jack = factory.createIRI("Jack");
     hates = factory.createIRI("hates");
     kevin = factory.createIRI("Kevin");
        datasetGraphView = (DatasetGraphView) dataset.getGraph();
        unionDataset = new DatasetGraphView(dataset);
    }

    @Test
    public void test_addTriple() {
        Triple triple = factory.createTriple(jack, hates, kevin);
        datasetGraphView.add(triple);
        assertTrue(datasetGraphView.contains(triple));
    }

    @Test
    public void test_addTripleSeparably() {
        Triple triple = factory.createTriple(jack, hates, kevin);
        datasetGraphView.add(jack,hates,kevin);
        assertTrue(datasetGraphView.contains(triple));
    }

    @Test
    public void test_Clear() {
        datasetGraphView.add(jack,hates,kevin);
        assertTrue(datasetGraphView.size() > 0);
        datasetGraphView.clear();
        assertTrue(datasetGraphView.size() == 0);
    }

    @Test
    public void test_removeTriple() {
        Triple triple = factory.createTriple(jack, hates, kevin);
        datasetGraphView.add(triple);
        assertTrue(datasetGraphView.contains(triple));
        datasetGraphView.remove(triple);
        assertFalse(datasetGraphView.contains(triple));
    }

    @Test
    public void test_removeTripleSeparably() {
        Triple triple = factory.createTriple(jack, hates, kevin);
        datasetGraphView.add(jack,hates,kevin);
        assertTrue(datasetGraphView.contains(triple));
        datasetGraphView.remove(jack,hates,kevin);
        assertFalse(datasetGraphView.contains(triple));
    }

    @Test
    public void test_unionGraph() {
        Triple triple = factory.createTriple(jack, hates, kevin);
        unionDataset.add(triple);
        unionDataset.add(triple);
        assertEquals(1, datasetGraphView.stream(jack,hates,kevin).count());
    }

}