package org.rekdev.guava.microbenchmark;

import java.util.*;

import com.google.common.collect.*;

public class MultiMapMicroBenchmark {

    interface DoIt {
        void doIt( int iterations, String[] keys );
    }

    static class DoIt_____Map implements DoIt {
        @Override
        public void doIt( int iterations, String[] keys ) {
            Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
            for ( int i = 0; i < iterations; i++ ) {
                String key = keys[i % keys.length];
                List<Integer> list = map.get( key );
                if ( list == null ) {
                    list = new ArrayList<Integer>();
                    map.put( key, list );
                }
                list.add( i );
            }
        }

    }

    static class DoItMultimap implements DoIt {
        @Override
        public void doIt( int iterations, String[] keys ) {
            Multimap<String, Integer> multiMap = ArrayListMultimap.create();
            for ( int i = 0; i < iterations; i++ ) {
                String key = keys[i % keys.length];
                multiMap.put( key, i );
            }
        }
    }

    public static void main( String[] args ) {
        String[] keys = { "Zeros", "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes", "Sevens", "Eights", "Nines" };
        int[] iterations = { 50000000, 51250000, 52500000, 55000000 };
        DoIt[] contestents = { new DoItMultimap(), new DoIt_____Map() };
        for ( int i = 0; i < iterations.length; i++ ) {
            int nFails = 0;
            for ( DoIt contestent : contestents ) {
                long startTime = System.currentTimeMillis();
                String result = null;
                try {
                    contestent.doIt( iterations[i], keys );
                    result = "Success";
                } catch ( Error e ) {
                    result = "Fail " + e.getMessage();
                    nFails++;
                } finally {
                    long elapsed = System.currentTimeMillis() - startTime;
                    String name = contestent.getClass().getSimpleName();
                    System.out.println( String.format( "%s @ %,d: %s, elapsed: %d ms", name, iterations[i], result, elapsed ) );
                }
            }
            if ( nFails == contestents.length ) {
                System.out.println( String.format( "Everyone failed @ %,d, I'm out of here.", iterations[i] ) );
                break;
            }
        }
    }

}
