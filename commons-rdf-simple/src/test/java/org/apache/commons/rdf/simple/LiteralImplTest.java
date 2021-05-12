package org.apache.commons.rdf.simple;
import static org.junit.Assert.*;

import org.apache.commons.rdf.api.Literal;
import org.junit.Test;

public class LiteralImplTest {

	/**
	 * Test if it throws IllegalArgumentException
	 */
    @Test
    public void test_ConstructorThrowsException_IriDataType() {
        Exception ex = null;
        try {
            Literal literal = new LiteralImpl("random stuff", Types.RDF_LANGSTRING);
        } catch (Exception exception) {
            ex = exception;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof IllegalArgumentException);
        assertTrue(ex.getMessage().startsWith("Cannot create a non-language literal with type "));
    }

    /**
     * Test if it throws NullPointerException, sadly can't reach IllegalArgumentException
     */
    @Test
    public void test_ConstructorThrowsException_LanguageTag() {
        Exception ex = null;
        try {
            Literal literal = new LiteralImpl("random stuff", (String) null);
        } catch (Exception exception) {
            ex = exception;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof NullPointerException);
        //IllegalArgumentException cannot be reached because of Objects.requireNonNull()
    }
}