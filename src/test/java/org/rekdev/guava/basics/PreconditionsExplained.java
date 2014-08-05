package org.rekdev.guava.basics;

import static com.google.common.base.Preconditions.*;
import static org.junit.Assert.*;

import java.text.*;
import java.util.*;

import org.junit.*;

import com.google.common.collect.*;

public class PreconditionsExplained {
    @Test
    public void test_checkNotNull() {
        Integer i = null;
        try {
            checkNotNull( i );
        } catch ( Exception e ) {
            assertTrue( e instanceof NullPointerException );
            assertNull( e.getMessage() );
        }
        try {
            checkNotNull( i, "Error Message" );
        } catch ( Exception e ) {
            assertTrue( e instanceof NullPointerException );
            assertEquals( "Error Message", e.getMessage() );
        }
        try {
            checkNotNull( i, "Hello %s %s", "World" );
        } catch ( Exception e ) {
            assertTrue( e instanceof NullPointerException );
            assertEquals( "Hello World %s", e.getMessage() );
        }
        try {
            i = checkNotNull( Integer.valueOf( 714 ) );
            assertEquals( Integer.valueOf( 714 ), i );
        } catch ( Exception e ) {
            fail();
        }
    }

    @Test
    public void test_checkArgument() {
        Integer one = Integer.valueOf( 1 );
        Integer two = Integer.valueOf( 2 );
        try {
            checkArgument( two.equals( one ) );
        } catch ( Exception e ) {
            assertTrue( e instanceof IllegalArgumentException );
            assertNull( e.getMessage() );
        }
        try {
            checkArgument( two.equals( one ), "Error Message" );
        } catch ( Exception e ) {
            assertTrue( e instanceof IllegalArgumentException );
            assertEquals( "Error Message", e.getMessage() );
        }
        try {
            checkArgument( two.equals( one ), "Expected %s.equals( %s ) to be true.  %s", one, two, null );
        } catch ( Exception ie ) {
            assertTrue( ie instanceof IllegalArgumentException );
            assertEquals( "Expected 1.equals( 2 ) to be true.  null", ie.getMessage() );
        }
        try {
            checkArgument( one.compareTo( two ) < 0, "Expected %s < %s", one, two );
        } catch ( Exception e ) {
            fail();
        }
    }

    @Test
    public void test_checkState() {
        DateFormat df = new SimpleDateFormat( "yyyyMMdd HH:mm:ss" );
        try {
            Date then = df.parse( "20120101 00:00:00" );
            Date now = new Date();

            try {
                checkState( now.equals( then ) );
            } catch ( Exception e ) {
                assertTrue( e instanceof IllegalStateException );
                assertNull( e.getMessage() );
            }
            try {
                checkState( now.equals( then ), "Error Message" );
            } catch ( Exception e ) {
                assertTrue( e instanceof IllegalStateException );
                assertEquals( "Error Message", e.getMessage() );
            }
            try {
                checkState( now.equals( then ), "Expected %s.equals( %s ) to be true.  %s", now, then, null );
            } catch ( Exception e ) {
                assertTrue( e instanceof IllegalStateException );
                String expected = String.format( "Expected %s.equals( %s ) to be true.  null", now, then );
                assertEquals( expected, e.getMessage() );
            }
            try {
                checkState( now.compareTo( then ) > 0, "Expected %s > %s", now, then );
            } catch ( Exception e ) {
                fail();
            }

        } catch ( ParseException e ) {
            e.printStackTrace();
            fail( "DateFormat parse failure" );
        }
    }

    @Test
    public void test_checkIndexStuff() {
        ImmutableList<String> list = ImmutableList.of( "One", "Two", "Three", "Four", "Five" );
        int sizeOfList = list.size();

        checkElementIndex( 0, sizeOfList );
        checkElementIndex( 2, sizeOfList );
        checkElementIndex( sizeOfList - 1, sizeOfList );
        try {
            // Note: no version that takes varargs with params for the message
            checkElementIndex( -1, sizeOfList, "Exceptional Message" );
        } catch ( Exception e ) {
            assertTrue( e instanceof IndexOutOfBoundsException );
            // AHH! They "enhance" the message
            // assertEquals( "Exceptional Message", e.getMessage() );
            assertTrue( e.getMessage().matches( "Exceptional Message.*" ) );
        }
        try {
            checkElementIndex( sizeOfList, sizeOfList, "Exceptional Message" );
        } catch ( Exception e ) {
            assertTrue( e instanceof IndexOutOfBoundsException );
            assertTrue( e.getMessage().matches( "Exceptional Message.*" ) );
        }
        // I really don't understand the inclusive nature of checkPositionIndex,
        // why is 0 legit, then?
        checkPositionIndex( 0, sizeOfList );
        checkPositionIndex( 2, sizeOfList );
        checkPositionIndex( sizeOfList, sizeOfList );
    }

}
