package org.rekdev.guava.collections.gtug;

import java.util.*;

import com.google.common.collect.*;

public class UnmodifiableVsImmutable {
    public static final Set<Integer> oldSchool_LUCKY_NUMBERS;
    static {
        Set<Integer> set = new LinkedHashSet<Integer>();
        set.add( 7 );
        set.add( 14 );
        set.add( 42 );
        oldSchool_LUCKY_NUMBERS = Collections.unmodifiableSet( set );
    }

    public static final Set<Integer> littleBetter_LUCKY_NUMBERS = 
        Collections.unmodifiableSet( 
                new LinkedHashSet<Integer>(
                        Arrays.asList( 7, 14, 42 ) ) );

    // Guava gets it done in one line, one class, type inference, if you will...
    public static final ImmutableSet<Integer> LUCKY_NUMBERS = ImmutableSet.of( 7, 14, 42 );

    public static final Map<String,Integer> oldSchool_ENGLISH_TO_INT;
    static {
        Map<String,Integer> map = new LinkedHashMap<String,Integer>();
        map.put( "seven", 7 );
        map.put( "fourteen", 14 );
        map.put( "fortytwo", 42 );
        oldSchool_ENGLISH_TO_INT = Collections.unmodifiableMap( map );
    }

    // Guava builder for the map (replaces .with(), apparently...not as convenient but...meh...
    public static final ImmutableMap<String,Integer> ENGLISH_TO_INT =
            ImmutableMap.<String,Integer>builder()
            .put( "seven", 7 )
            .put( "fourteen", 14 )
            .put( "fortytwo", 42 )
            .build();
    
    // Guava also has .of overloads that handle up to 5 key value pairs...
    public static final ImmutableMap<String,Integer> ENGLISH_TO_INT2 = ImmutableMap.of(
            "seven", 7,
            "fourteen", 14,
            "fortytwo", 42 );
    
    public void oldSchoolDefensiveCopy( Set<Integer> numbers ) {
        Set<Integer> umNumbers = Collections.unmodifiableSet( new LinkedHashSet<Integer>( numbers ) );
        System.out.println( String.format( "umNumbers: %s", umNumbers.toString() ) );
    }
    
    public void guavaDefensiveCopy( Set<Integer> numbers ) {
        ImmutableSet<Integer> imNumbers = ImmutableSet.copyOf( numbers );
        System.out.println( String.format( "imNumbers: %s", imNumbers.toString() ) );
    }
}