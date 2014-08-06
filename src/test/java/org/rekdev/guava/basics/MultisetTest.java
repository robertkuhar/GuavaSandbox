package org.rekdev.guava.basics;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class MultisetTest {
    interface IsAnagram {
        boolean isAnagram( String s1, String s2 );
    }

    class GuavaIsAnagram implements IsAnagram {

        @Override
        public boolean isAnagram( String s1, String s2 ) {
            if ( s1 == s2 ) {
                return true;
            }
            if ( s1 != null && s1.equals( s2 ) ) {
                return true;
            }
            if ( s1 != null && s2 != null && s1.length() != s2.length() ) {
                return false;
            }
            Multiset<Character> s1CharDistrib = makeCharDistrib( s1 );
            Multiset<Character> s2CharDistrib = makeCharDistrib( s2 );
            return s1CharDistrib.equals( s2CharDistrib );
        }

        private Multiset<Character> makeCharDistrib( String s ) {
            Multiset<Character> charDistrib = HashMultiset.create();
            if ( s != null ) {
                for ( char c : s.toCharArray() ) {
                    charDistrib.add( c );
                }
            }
            return charDistrib;
        }
    }

    class OldSchoolIsAnagram implements IsAnagram {
        @Override
        public boolean isAnagram( String s1, String s2 ) {
            if ( s1 == s2 ) {
                return true;
            }
            if ( s1 != null && s1.equals( s2 ) ) {
                return true;
            }
            if ( s1 != null && s2 != null && s1.length() != s2.length() ) {
                return false;
            }
            Map<Character, Integer> s1Counts = makeCharDistributionMap( s1 );
            Map<Character, Integer> s2Counts = makeCharDistributionMap( s2 );
            return s1Counts.equals( s2Counts );
        }

        private Map<Character, Integer> makeCharDistributionMap( String s ) {
            Map<Character, Integer> counts = new HashMap<Character, Integer>();
            if ( s != null ) {
                for ( char c : s.toCharArray() ) {
                    // Guava MultiSet would turn this into one line...
                    Integer count = counts.get( c );
                    if ( count == null ) {
                        counts.put( c, 1 );
                    } else {
                        counts.put( c, count + 1 );
                    }
                }
            }
            return counts;
        }
    }

    private boolean isAnagram( String s1, String s2, IsAnagram isAnagram ) {
        return isAnagram.isAnagram( s1, s2 );
    }

    @Test
    public void happyPath() {
        OldSchoolIsAnagram oldSchool = new OldSchoolIsAnagram();
        GuavaIsAnagram guava = new GuavaIsAnagram();

        assertTrue( isAnagram( "abc", "abc", oldSchool ) );
        assertTrue( isAnagram( "abc", "abc", guava ) );

        assertTrue( isAnagram( "abc", "cba", oldSchool ) );
        assertTrue( isAnagram( "abc", "cba", guava ) );

        assertFalse( isAnagram( "abc", "xyz", oldSchool ) );
        assertFalse( isAnagram( "abc", "xyz", guava ) );

        assertTrue( isAnagram( "aabbcc", "abccab", oldSchool ) );
        assertTrue( isAnagram( "aabbcc", "abccab", guava ) );

        assertFalse( isAnagram( "abc", "abccab", oldSchool ) );
        assertFalse( isAnagram( "abc", "abccab", guava ) );
    }

    @Test
    public void edgeCases() {
        OldSchoolIsAnagram oldSchool = new OldSchoolIsAnagram();
        GuavaIsAnagram guava = new GuavaIsAnagram();

        assertTrue( isAnagram( null, null, oldSchool ) );
        assertTrue( isAnagram( null, null, guava ) );

        assertTrue( isAnagram( "", "", oldSchool ) );
        assertTrue( isAnagram( "", "", guava ) );

        assertFalse( isAnagram( "a", null, oldSchool ) );
        assertFalse( isAnagram( "a", null, guava ) );

        assertFalse( isAnagram( null, "a", oldSchool ) );
        assertFalse( isAnagram( null, "a", guava ) );
    }
}
