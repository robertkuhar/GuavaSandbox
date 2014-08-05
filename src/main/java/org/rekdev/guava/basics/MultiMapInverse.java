package org.rekdev.guava.basics;

import java.util.*;

import com.google.common.collect.*;

public class MultiMapInverse {
    public static void main( String[] args ) {
        ImmutableMultimap.Builder<String, String> builder = new ImmutableMultimap.Builder<String, String>();
        builder.putAll( "type1", Arrays.asList( "FieldA", "FieldB", "FieldC") );
        builder.putAll( "type3", Arrays.asList( "FieldC", "FieldD", "FieldE") );
        builder.putAll( "type2", Arrays.asList( "FieldB", "FieldC", "FieldD") );
        ImmutableMultimap<String, String> typesToFields = builder.build();
        ImmutableMultimap<String, String> fieldsToTypes = typesToFields.inverse();
        
        System.out.println( "typesToFields: " + typesToFields );
        System.out.println( "fieldsToTypes: " + fieldsToTypes );
        
    }
}
