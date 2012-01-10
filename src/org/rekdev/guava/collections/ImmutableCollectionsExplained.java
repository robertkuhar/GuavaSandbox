package org.rekdev.guava.collections;

import java.awt.*;
import java.util.*;

import com.google.common.collect.*;

public class ImmutableCollectionsExplained {

    // .of static method
    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of( "red", "orange", "yellow", "green", "blue", "purple" );

    class Bar {
    }

    class Foo {
        Set<Bar> bars;

        Foo( Set<Bar> bars ) {
            // .copyOf static method
            this.bars = ImmutableSet.copyOf( bars ); // Defensive copy
        }
    }
    
    public static ImmutableSet<Color> WEBSAFE_COLORS = ImmutableSet.of( new Color( 0, 0, 0 ), new Color( 255, 255, 255 ) );

    public static final ImmutableSet<Color> GOOGLE_COLORS = 
        ImmutableSet.<Color> builder()
        .addAll( WEBSAFE_COLORS )
        .add( new Color( 0, 191, 255 ) )
        .build();
}
