package org.rekdev.guava.basics;

import static org.junit.Assert.*;

import org.junit.*;

import com.google.common.base.*;

public class UsingAndAvoidingNullExplained {

    /**
     * Exercise the static factory methods for making Optional instances.
     */
    @Test
    public void makingAnOptional() {
        Optional<Integer> possible = Optional.of( 5 );
        assertTrue( possible.isPresent() );
        assertEquals( Integer.valueOf( 5 ), possible.get() );

        possible = Optional.absent();
        assertFalse( possible.isPresent() );
        try {
            assertNull( possible.get() );
            fail();
        } catch ( IllegalStateException e ) {
            assertTrue( true );
        }

        Integer fortyTwo = new Integer( 42 );
        possible = Optional.fromNullable( fortyTwo );
        assertTrue( possible.isPresent() );
        assertEquals( Integer.valueOf( 42 ), possible.get() );

        fortyTwo = null;
        possible = Optional.fromNullable( fortyTwo );
        assertFalse( possible.isPresent() );
    }

    /**
     * Exercise query methods on Optional
     */
    @Test
    public void queryMethods() {
        Integer fortyTwo = null;
        Optional<Integer> possible = Optional.fromNullable( fortyTwo );
        assertFalse( possible.isPresent() );
        assertEquals( Integer.valueOf( 0 ), possible.or( 0 ) );
        assertNull( possible.orNull() );

        fortyTwo = new Integer( 42 );
        possible = Optional.fromNullable( fortyTwo );
        assertTrue( possible.isPresent() );
        assertEquals( fortyTwo, possible.or( 0 ) );
        assertNotNull( possible.orNull() );
    }

    /**
     * Strings utility methods.
     */
    @Test
    public void convenienceMethodsOnStrings() {
        assertNull( Strings.emptyToNull( "" ) );
        // NOTE: they don't trim. Its got to be truly empty.
        assertNotNull( Strings.emptyToNull( " " ) );

        assertTrue( Strings.isNullOrEmpty( "" ) );
        assertTrue( Strings.isNullOrEmpty( null ) );
        // NOTE: they don't trim. Its got to be truly empty.
        assertFalse( Strings.isNullOrEmpty( " " ) );

        assertEquals( "", Strings.nullToEmpty( null ) );
        // NOTE: they don't trim. Its got to be truly empty.
        assertEquals( " ", Strings.nullToEmpty( " " ) );
    }
}
